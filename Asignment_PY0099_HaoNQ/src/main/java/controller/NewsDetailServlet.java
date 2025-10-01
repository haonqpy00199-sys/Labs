package controller;

import java.io.IOException;
import dao.NewsDAO;
import model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/news-detail")
public class NewsDetailServlet extends HttpServlet {
    private final NewsDAO newsDAO = new NewsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            if (idParam == null || !idParam.matches("\\d+")) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
                return;
            }

            int id = Integer.parseInt(idParam);
            News news = newsDAO.findById(id);

            if (news == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy bài viết");
                return;
            }

            // Tăng lượt xem
            newsDAO.increaseViewCount(id);

            req.setAttribute("news", news);
            req.getRequestDispatcher("/WEB-INF/views/news-detail.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
