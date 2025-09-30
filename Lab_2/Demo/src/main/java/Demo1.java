import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/demo/index")
public class Demo1 extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse reps)
	throws ServletException, IOException{
		
	}
}
