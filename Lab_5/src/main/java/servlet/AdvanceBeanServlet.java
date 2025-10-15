package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.AdvanceBean;
import models.Staff;

@WebServlet({"/bean/advance/form.php", "/bean/advance/submit.php"})
public class AdvanceBeanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
        	DateTimeConverter dtc= new DateConverter(new Date());
        	dtc.setPattern("MM/dd/yyyy");
        	ConvertUtils.register(dtc, Date.class);
        	
        	// thiết lập advance bean model
        	
        	AdvanceBean advanceBean= new AdvanceBean();
            Map<String, String[]> paramMap = req.getParameterMap();
            
            // đổ dữ liệu từ form trên web vào model thông qua hàm beanutils
            BeanUtils.populate(advanceBean, paramMap);
            System.out.println("Dữ liệu sau khi đổ");
            System.out.println("> Hobby: " + advanceBean.getHobby());
            System.out.println("> Date: " + advanceBean.getDate());
            System.out.println("> Photo: " + advanceBean.getPhoto());
            
            // xử lí đường dẫn photo và set photo vào bean
            Part part= req.getPart("photo_file");
            advanceBean.setPhoto(part.getSubmittedFileName());
            
       
        } catch (Exception e) {
            throw new ServletException("Lỗi dữ liệu", e);
        }
    }
}