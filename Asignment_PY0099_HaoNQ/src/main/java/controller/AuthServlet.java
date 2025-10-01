package controller;

import dao.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet({"/login", "/logout", "/register"})
public class AuthServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/login":
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                break;
            case "/register":
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                break;
            case "/logout":
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + "/home");
                break;
            default:
                resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();
        try {
            if ("/login".equals(path)) {
                String email = req.getParameter("email");
                String pw    = req.getParameter("password");
                User u = userDAO.login(email, pw);
                if (u == null) {
                    req.setAttribute("error", "Sai email hoặc mật khẩu");
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                    return;
                }
                req.getSession().setAttribute("user", u);
                // true = admin, false = reporter
                resp.sendRedirect(req.getContextPath()
                        + (u.isRole() ? "/admin/dashboard" : "/reporter/dashboard"));
                return;
            }

            if ("/register".equals(path)) {
                // xử lý đăng ký… (nếu cần, bạn đã có ở bước trước)
                resp.sendError(501); // tạm thời
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
