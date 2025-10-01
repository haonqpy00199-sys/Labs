package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/calculate/add", "/calculate/sub"})
public class CalculateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("message", "Nhập số và chọn phép tính");
        req.getRequestDispatcher("/views/calculate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String a = req.getParameter("a");
        String b = req.getParameter("b");
        String path = req.getServletPath();

        try {
            double x = Double.parseDouble(a);
            double y = Double.parseDouble(b);
            double c;

            if (path.endsWith("/add")) {
                c = x + y;
                req.setAttribute("message", a + " + " + b + " = " + c);
            } else {
                c = x - y;
                req.setAttribute("message", a + " - " + b + " = " + c);
            }
        } catch (Exception e) {
            req.setAttribute("message", "Vui lòng nhập số hợp lệ!");
        }

        req.getRequestDispatcher("/views/calculate.jsp").forward(req, resp);
    }
}