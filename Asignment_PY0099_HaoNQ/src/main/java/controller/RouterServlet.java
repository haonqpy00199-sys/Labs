package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/index")
public class RouterServlet extends HttpServlet {

    private static final Map<String, String> VIEW_MAP = new HashMap<>();
    static {
        VIEW_MAP.put("/",            "/WEB-INF/views/home.jsp");
        VIEW_MAP.put("/home",        "/WEB-INF/views/home.jsp");
        VIEW_MAP.put("/category",    "/WEB-INF/views/category.jsp");
        VIEW_MAP.put("/search",      "/WEB-INF/views/search.jsp");
        VIEW_MAP.put("/login",       "/WEB-INF/views/login.jsp");
        VIEW_MAP.put("/register",    "/WEB-INF/views/register.jsp");
        VIEW_MAP.put("/profile",     "/WEB-INF/views/profile.jsp");
       
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath(); // ví dụ: "/category"
        String view = VIEW_MAP.get(path);
        if (view == null) {
            resp.sendError(404);
            return;
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }
    
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp); // hoặc route(req, resp);
    }

}
