import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/crud/create", "/crud/update", "/crud/delete", "/crud/edit/2024"})
public class CrudServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String uri = req.getRequestURI();
        String message = "";

        if(uri.contains("create")) {
        	this.create(req, resp);
            message = "Bạn đang gọi chức năng CREATE";
        } else if(uri.contains("update")) {
        	this.update(req, resp);
            message = "Bạn đang gọi chức năng UPDATE";
        } else if(uri.contains("delete")) {
        	this.delete(req, resp);
            message = "Bạn đang gọi chức năng DELETE";
        } else if(uri.contains("edit/2024")) {
        	this.edit(req, resp);
            message = "Bạn đang gọi chức năng EDIT 2024";
        }

        // Gửi message sang JSP
        req.setAttribute("message", message);
        req.getRequestDispatcher("/views/crud/index.jsp").forward(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("message", "Bạn đang gọi chức năng CREATE");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("message", "Bạn đang gọi chức năng UPDATE");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("message", "Bạn đang gọi chức năng DELETE");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("message", "Bạn đang gọi chức năng EDIT 2024");
    }
}

