import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/poly/Servlet")
public class UrlInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Lấy thông tin từ request
        String url = req.getRequestURL().toString();
        System.out.println(url);
        String uri = req.getRequestURI();
        System.out.println(uri);
        String queryString = req.getQueryString();
        System.out.println(queryString);
        String servletPath = req.getServletPath();
        System.out.println(servletPath);
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        String pathInfo = req.getPathInfo();
        System.out.println(pathInfo);
        String method = req.getMethod();
        System.out.println(method);

        // Xuất ra trình duyệt
        out.println("<h2>Thông tin địa chỉ URL</h2>");
        out.println("<ul>");
        out.println("<li><b>URL:</b> " + url + "</li>");
        out.println("<li><b>URI:</b> " + uri + "</li>");
        out.println("<li><b>QueryString:</b> " + queryString + "</li>");
        out.println("<li><b>ServletPath:</b> " + servletPath + "</li>");
        out.println("<li><b>ContextPath:</b> " + contextPath + "</li>");
        out.println("<li><b>PathInfo:</b> " + pathInfo + "</li>");
        out.println("<li><b>Method:</b> " + method + "</li>");
        out.println("</ul>");
    }
}
