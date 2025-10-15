package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.Base64Util;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // kiểm tra cookie đã lưu tên đăng nhập và mật khẩu hay chưa
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encoded = cookie.getValue();
                    String decodedText = Base64Util.decode(encoded);
                    String[] userInfo = decodedText.split(",");
                    if (userInfo.length == 2) {
                        req.setAttribute("username", userInfo[0]);
                        req.setAttribute("password", userInfo[1]);
                    }
                }
            }
        }
      
        // 2. Forward to JSP
        req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberMe");
        HttpSession session = req.getSession();

        // 1. kiểm tra tên đăng nhập và mật khẩu
        if ("FPT".equalsIgnoreCase(username) && "poly".equals(password)) {

            req.setAttribute("message", "Login successfully!");

            // 2. lưu username trong session
            session.setAttribute("sessionUsername", username);

            // 3. nếu đánh vào remember me thì lưu username và password vào cookie
            if (remember != null) {
                String value = username + "," + password ;
                String userInfo = Base64Util.encode(value);

                Cookie cookie = new Cookie("user", userInfo);
                cookie.setMaxAge(10 * 60 * 60); // 10 hours
                cookie.setPath("/");
                resp.addCookie(cookie);
            } else {
            	// xóa cookie
            	Cookie cookie = new Cookie("user", "");
                cookie.setMaxAge(0); // 10 hours
                cookie.setPath("/");
                resp.addCookie(cookie);
            	
            }
        } else {
            // nếu mà login sai thì xóa username trong session
            session.removeAttribute("sessionUsername");
            req.setAttribute("message", "Invalid login info!");
        }

        // trở lại trang login
        req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
    }
}
