package controller;

import dao.CategoryDAO;
import dao.NewsDAO;
import model.Category;
import model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final NewsDAO newsDAO = new NewsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);

            Category current = categoryDAO.findById(id);
            if (current == null) {
                resp.sendError(404, "Category not found");
                return;
            }

            List<News> news = newsDAO.findByCategory(id); // code ở bước 2
            req.setAttribute("currentCategory", current);
            req.setAttribute("news", news != null ? news : Collections.emptyList());

            // menu
            req.setAttribute("categories", categoryDAO.findAll());

            req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
