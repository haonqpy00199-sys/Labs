package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@WebServlet("/upload-image")
@MultipartConfig(fileSizeThreshold = 2 * 1024 * 1024, // 2MB bộ nhớ đệm
        maxFileSize = 10 * 1024 * 1024,               // 10MB / ảnh
        maxRequestSize = 50 * 1024 * 1024)            // 50MB / request
public class UploadImageServlet extends HttpServlet {

    private static final Set<String> ALLOW_EXT = Set.of("jpg","jpeg","png","gif","webp");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Part part = req.getPart("upload"); // CKEditor 5 dùng tên field mặc định "upload"
        if (part == null || part.getSize() == 0) {
            send400(resp, "No file");
            return;
        }
        String submitted = Path.of(part.getSubmittedFileName()).getFileName().toString();
        String ext = "";
        int dot = submitted.lastIndexOf('.');
        if (dot > 0) ext = submitted.substring(dot + 1).toLowerCase();

        String mime = part.getContentType() == null ? "" : part.getContentType().toLowerCase();
        if (!mime.startsWith("image/") || !ALLOW_EXT.contains(ext)) {
            send400(resp, "Only image allowed");
            return;
        }

        String newName = System.currentTimeMillis()+"_"+UUID.randomUUID().toString().substring(0,8)+"."+ext;

        String webPath = "/assets/uploads/" + newName; // đường dẫn dùng để truy cập từ trình duyệt
        Path diskDir = Path.of(getServletContext().getRealPath("/assets/uploads"));
        Files.createDirectories(diskDir);
        Path dest = diskDir.resolve(newName);

        try (InputStream is = part.getInputStream()) {
            Files.copy(is, dest, StandardCopyOption.REPLACE_EXISTING);
        }

        // Trả JSON theo CKEditor5 SimpleUploadAdapter: { "url": "..." }
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write("{\"url\":\"" + req.getContextPath() + webPath + "\"}");
    }

    private void send400(HttpServletResponse resp, String msg) throws IOException {
        resp.setStatus(400);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write("{\"error\":\"" + msg + "\"}");
    }
}
