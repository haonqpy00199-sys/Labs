import java.io.IOError;
import java.io.IOException;
import java.rmi.ServerException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lab_1/hello.php")
public class HelloServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	throws ServerException, IOException{
		resp.getWriter().println("<h1> FPT Polytechnic </h1>");
	}
}
