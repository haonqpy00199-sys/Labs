package controller;

import dao.CategoryDAO;
import dao.NewsDAO;
import model.News;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@WebServlet("/reporter/post-create")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class ReporterPostCreateServlet extends HttpServlet {

    private final NewsDAO newsDAO = new NewsDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private static final Set<String> ALLOW_EXT = Set.of("jpg","jpeg","png","gif","webp");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("categories", categoryDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/reporter/post-create.jsp").forward(req, resp);
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User me = (User) req.getSession().getAttribute("user");
        if (me == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content"); // CKEditor trả HTML
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        boolean home = "1".equals(req.getParameter("home"));

        // xử lý thumbnail (tùy chọn)
        String thumbnailPath = null;
        Part thumb = req.getPart("thumbnail");
        if (thumb != null && thumb.getSize() > 0) {
            String sub = Path.of(thumb.getSubmittedFileName()).getFileName().toString();
            String ext = sub.contains(".") ? sub.substring(sub.lastIndexOf('.')+1).toLowerCase() : "";
            String mime = thumb.getContentType() == null ? "" : thumb.getContentType().toLowerCase();
            if (mime.startsWith("image/") && ALLOW_EXT.contains(ext)) {
                String newName = System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().substring(0,8)+"."+ext;
                Path dir = Path.of(getServletContext().getRealPath("/assets/uploads"));
                Files.createDirectories(dir);
                try (InputStream is = thumb.getInputStream()) {
                    Files.copy(is, dir.resolve(newName), StandardCopyOption.REPLACE_EXISTING);
                }
                thumbnailPath = "assets/uploads/" + newName; // LƯU đường dẫn tương đối vào DB
            }
        }

        // Lưu DB
        try {
            News n = new News();
            n.setTitle(title);
            n.setContent(content); // HTML
            n.setImage(thumbnailPath); // ảnh đại diện (có thể null)
            n.setPostedDate(LocalDateTime.now());
            n.setAuthor(me.getFullname());
            n.setViewCount(0);
            n.setCategoryId(categoryId);
            n.setHome(home);
            n.setApproved(false);    // chờ admin duyệt
            n.setReporterId(me.getId());

            int newId = newsDAO.create(n);
            resp.sendRedirect(req.getContextPath() + "/reporter/dashboard?created=" + newId);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
