import java.io.IOException;
import java.rmi.ServerException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home/index")
public class HomeServlet extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
	req.getRequestDispatcher("/views/indext.jsp").forward(req, resp);
}
}
