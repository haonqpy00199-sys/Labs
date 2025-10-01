package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dang-ky")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Hiển thị form
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String married = req.getParameter("married");
        String country = req.getParameter("country");
        String notes = req.getParameter("notes");

        // hobbies là tham số đa trị
        String[] hobbies = req.getParameterValues("hobbies");

        System.out.println("---- THÔNG TIN ĐĂNG KÝ ----");
        System.out.println("Tên đăng nhập: " + username);
        System.out.println("Mật khẩu: " + password);
        System.out.println("Giới tính: " + gender);
        System.out.println("Đã có gia đình: " + married);
        System.out.println("Quốc tịch: " + country);

        if (hobbies != null) {
            System.out.print("Sở thích: ");
            for (String h : hobbies) {
                System.out.print(h + " ");
            }
            System.out.println();
        }

        System.out.println("Ghi chú: " + notes);
    }
}