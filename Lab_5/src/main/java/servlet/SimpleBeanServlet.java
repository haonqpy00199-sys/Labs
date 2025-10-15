package servlet;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Staff;

@WebServlet({"/bean/simple/form.php", "/bean/simple/submit.php"})
public class SimpleBeanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bean/simple.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Staff staffBean = new Staff();
            Map<String, String[]> paramMap = req.getParameterMap();

            // đổ dữ liệu từ form trên web vào model thông qua hàm beanutils
            BeanUtils.populate(staffBean, paramMap);
            System.out.println("Dữ liệu sau khi đổ");
            System.out.println("> Fullname: " + staffBean.getFullname());
            System.out.println("> Age: " + staffBean.getAge());
            System.out.println("> isGender: " + staffBean.isGender());
            System.out.println("> Country: " + staffBean.getCountry());

            // set dữ liệu attribute vào bean để truyền vào trang sau
            req.setAttribute("staffBean", staffBean);
       

        } catch (Exception e) {
            throw new ServletException("Lỗi dữ liệu", e);
        }
        this.doGet(req, resp);
    }
}