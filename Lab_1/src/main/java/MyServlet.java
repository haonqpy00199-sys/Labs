import java.io.IOException;
import java.rmi.ServerException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/Lab_1/servlet")
public class MyServlet extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String method = req.getMethod();
		System.out.println(method);
	}

}
