package controller;

import dao.CategoryDAO;
import dao.NewsDAO;
import dao.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet({
        "/admin/dashboard",
        "/admin/news-approve",
        "/admin/users",
        "/admin/categories",
        "/admin/settings"
})
public class AdminServlet extends HttpServlet {

    private final NewsDAO newsDAO = new NewsDAO();
    private final UserDAO userDAO = new UserDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    /* ---------- Helpers ---------- */
    private boolean isAdmin(HttpServletRequest req) {
        Object o = req.getSession().getAttribute("user");
        return (o instanceof User) && ((User) o).isRole(); // true = admin
    }
    private void deny(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    /* ---------- GET: hiển thị trang ---------- */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!isAdmin(req)) { deny(req, resp); return; }

        String path = req.getServletPath();
        try {
            switch (path) {
                case "/admin/dashboard": {
                    // vài con số tổng quan
                    req.setAttribute("pendingCount", newsDAO.countPending());
                    req.setAttribute("newsTotal", newsDAO.countAll());
                    req.setAttribute("usersTotal", userDAO.countAll());
                    req.setAttribute("categories", categoryDAO.findAll());
                    req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, resp);
                    break;
                }
                case "/admin/news-approve": {
                    // danh sách bài chờ duyệt
                    req.setAttribute("pendingList", newsDAO.findPending());
                    req.getRequestDispatcher("/WEB-INF/views/admin/news-approve.jsp").forward(req, resp);
                    break;
                }
                case "/admin/users": {
                    req.setAttribute("users", userDAO.findAll());
                    req.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(req, resp);
                    break;
                }
                case "/admin/categories": {
                    req.setAttribute("categories", categoryDAO.findAll());
                    req.getRequestDispatcher("/WEB-INF/views/admin/categories.jsp").forward(req, resp);
                    break;
                }
                case "/admin/settings": {
                    req.getRequestDispatcher("/WEB-INF/views/admin/settings.jsp").forward(req, resp);
                    break;
                }
                default:
                    resp.sendError(404);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /* ---------- POST: thao tác quản trị ---------- */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!isAdmin(req)) { deny(req, resp); return; }

        String path = req.getServletPath();
        String action = req.getParameter("action"); // tên hành động từ form
        if (action == null) action = "";

        try {
            switch (path) {
                /* ---- DUYỆT BÀI ---- */
                case "/admin/news-approve": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    switch (action) {
                        case "approve":
                            newsDAO.setApproved(id, true);
                            break;
                        case "reject": // xoá hoặc set Approved=false
                            newsDAO.delete(id); // hoặc newsDAO.setApproved(id, false);
                            break;
                        case "home-on":
                            newsDAO.setHome(id, true);
                            break;
                        case "home-off":
                            newsDAO.setHome(id, false);
                            break;
                    }
                    resp.sendRedirect(req.getContextPath() + "/admin/news-approve");
                    return;
                }

                /* ---- NGƯỜI DÙNG ---- */
                case "/admin/users": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    switch (action) {
                        case "activate":
                            userDAO.setActivated(id, true); break;
                        case "deactivate":
                            userDAO.setActivated(id, false); break;
                        case "promote":   // thành admin
                            userDAO.setRole(id, true);  break;
                        case "demote":    // thành reporter
                            userDAO.setRole(id, false); break;
                        case "delete":
                            userDAO.delete(id); break;
                    }
                    resp.sendRedirect(req.getContextPath() + "/admin/users");
                    return;
                }

                /* ---- CHUYÊN MỤC ---- */
                case "/admin/categories": {
                    switch (action) {
                        case "create": {
                            String name = req.getParameter("name");
                            categoryDAO.create(name);
                            break;
                        }
                        case "update": {
                            int id = Integer.parseInt(req.getParameter("id"));
                            String name = req.getParameter("name");
                            categoryDAO.update(id, name);
                            break;
                        }
                        case "delete": {
                            int id = Integer.parseInt(req.getParameter("id"));
                            categoryDAO.delete(id);
                            break;
                        }
                    }
                    resp.sendRedirect(req.getContextPath() + "/admin/categories");
                    return;
                }

                /* ---- SETTINGS ---- */
                case "/admin/settings": {
                    // tuỳ bạn muốn lưu gì (ví dụ cấu hình site)
                    resp.sendRedirect(req.getContextPath() + "/admin/settings?ok=1");
                    return;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
