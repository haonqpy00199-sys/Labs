package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/news")
public class StringServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();
        map.put("title", "Tiêu đề bản tin");
        map.put("content", "Nội dung bản tin thường rất dài, "
                + "chúng ta chỉ lấy 100 ký tự đầu tiên để hiển thị, "
                + "phần còn lại thì bỏ đi hoặc hiển thị dấu ... để người đọc biết rằng vẫn còn.");

        req.setAttribute("item", map);

        req.getRequestDispatcher("/views/news.jsp").forward(req, resp);
    }
}
