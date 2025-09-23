import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/form/update")
public class FormServlet extends HttpServlet{
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        Map<String, Object> map = new HashMap<>();
	        map.put("fullname", "Nguyễn Văn Tèo");
	        map.put("gender", true);
	        map.put("country", "VN");

	        req.setAttribute("user", map);
	        req.setAttribute("editabled", true);

	        req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
	    }
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        String fullname = req.getParameter("fullname");
	        System.out.println(fullname);

	        req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
	    }
	
}
