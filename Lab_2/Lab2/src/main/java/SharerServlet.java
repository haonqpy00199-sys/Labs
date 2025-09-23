
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home/index")
public class SharerServlet  extends HttpServlet{
@Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException{
	// set Attribute
	req.setAttribute("message","Welcome to FPT Polytechnic!");
	req.setAttribute("now", new Date());
	
	req.getRequestDispatcher("/views/page.jsp").forward(req,resp);
  }
}