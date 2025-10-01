package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // Lấy file upload
            Part photo = req.getPart("photo");

            // Tên file gốc
            String filename = photo.getSubmittedFileName();

            // Đường dẫn lưu file (thư mục /static/files)
            String path = "/static/files/" + filename;

            // Chuyển thành đường dẫn vật lý
            String realPath = req.getServletContext().getRealPath(path);

            // Lưu file vào server
            photo.write(realPath);

            // Gửi thông báo về JSP
            req.setAttribute("message", "Upload thành công: " + filename);
        } catch (Exception e) {
            req.setAttribute("message", "Upload thất bại: " + e.getMessage());
        }

        req.getRequestDispatcher("/views/upload.jsp").forward(req, resp);
    }
}