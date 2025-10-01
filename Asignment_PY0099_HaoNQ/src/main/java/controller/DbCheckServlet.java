package controller;

import util.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/db-check")
public class DbCheckServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        try (Connection cn = DB.getConnection()) {
            resp.getWriter().println("DB OK");
            resp.getWriter().println(DB.info());
        } catch (Throwable t) {
            // in FULL stacktrace ra response để thấy đúng lỗi gốc
            t.printStackTrace(resp.getWriter());
        }
    }
}

