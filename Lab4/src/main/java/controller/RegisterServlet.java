package controller;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Forward đến trang JSP
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Đọc các tham số đơn trị
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String married = req.getParameter("married");
        String country = req.getParameter("country");
        String note = req.getParameter("note");

        // Đọc các tham số đa trị
        String[] hobbies = req.getParameterValues("hobby");

        // Xuất ra console để kiểm tra
        System.out.println("Tên đăng nhập: " + username);
        System.out.println("Mật khẩu: " + password);
        System.out.println("Giới tính: " + gender);
        System.out.println("Đã có gia đình?: " + (married != null ? "Có" : "Chưa"));
        System.out.println("Quốc tịch: " + country);
        System.out.println("Sở thích: " + (hobbies != null ? Arrays.toString(hobbies) : "Không có"));
        System.out.println("Ghi chú: " + note);
        
        req.setAttribute("message", "Đã ghi nhận thông tin trên console!");
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }
}