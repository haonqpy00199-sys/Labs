package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dao.CategoryDAO;
import dao.NewsDAO;
import model.Category;
import model.News;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final NewsDAO newsDAO = new NewsDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1) Danh mục để render menu
        	List<Category> categories = safeList(() -> categoryDAO.findAll());
            req.setAttribute("categories", categories);

            // 2) Tin trang nhất (Home=true), ví dụ 10 bài
            List<News> approvedNews = safeList(() -> newsDAO.findApproved(10));
            req.setAttribute("approvedNews", approvedNews);

            // 3) Top 5 hot (view cao)
            List<News> hotList = safeList(() -> newsDAO.findTopHot(5));
            req.setAttribute("hotList", hotList);

            // 4) Top 5 mới (ngày đăng mới nhất)
            List<News> newList = safeList(() -> newsDAO.findTopNew(5));
            req.setAttribute("newList", newList);

            // 5) 5 tin xem gần đây (đọc từ cookie "recent" lưu id dạng: 31,22,10,...)
            List<Integer> recentIds = readRecentIdsFromCookie(req, "recent", 5);
            List<News> recentList = findNewsByIdsPreserveOrder(recentIds);
            req.setAttribute("recentList", recentList);

            // Forward sang trang chủ
            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);

        } catch (Exception e) {
            // Có lỗi thì log và hiển thị trang rỗng thân thiện
            e.printStackTrace();
            req.setAttribute("error", "Không tải được dữ liệu trang chủ: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
        }
    }

 // ----- Helpers -----
    @FunctionalInterface
    interface DaoCall<T> { T run() throws Exception; }

    /** Helper dành riêng cho List: nếu DAO ném exception thì trả về list rỗng đúng kiểu. */
    private static <E> java.util.List<E> safeList(DaoCall<java.util.List<E>> call) {
        try {
            return call.run();
        } catch (Exception e) {
            return java.util.Collections.emptyList();
        }
    }


    private List<Integer> readRecentIdsFromCookie(HttpServletRequest req, String name, int limit) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return Collections.emptyList();
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                String val = c.getValue(); // ví dụ: "31,22,10"
                if (val == null || val.isBlank()) return Collections.emptyList();
                List<Integer> ids = Arrays.stream(val.split(","))
                        .map(String::trim)
                        .filter(s -> s.matches("\\d+"))
                        .map(Integer::parseInt)
                        .distinct()
                        .limit(limit)
                        .collect(Collectors.toList());
                return ids;
            }
        }
        return Collections.emptyList();
    }

    /** Lấy tin theo danh sách id và giữ nguyên thứ tự ids (DAO không có findByIds thì gọi findById từng cái). */
    private List<News> findNewsByIdsPreserveOrder(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptyList();
        List<News> out = new ArrayList<>();
        for (Integer id : ids) {
            try {
                News n = newsDAO.findById(id);
                if (n != null) out.add(n);
            } catch (Exception ignored) {}
        }
        return out;
    }
}
