package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Staff;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
	 // Create a map with data
    Map<String, String[]> map = new HashMap<>();
    map.put("fullname", new String[] {"Nguyễn Văn Tèo"});
    map.put("salary", new String[] {"1500"});
    map.put("hobbies", new String[] {"Music", "Travelling"});

    // Create a Staff bean and populate it
    Staff staffBean = new Staff();
    staffBean.setFullname("Anh Teo");
    System.out.println(staffBean.getFullname()) ;
    
    
    
//    try {
//		BeanUtils.populate(staffBean, map);
//	} catch (IllegalAccessException e) {
//		System.out.println("Error about Illegal Access");
//		e.printStackTrace();
//	} catch (InvocationTargetException e) {
//		System.out.println("Error about Invocation Target");
//		e.printStackTrace();
//	}

    // ví dụ về simple bean
	// req.getRequestDispatcher("/views/Home.jsp").forward(req, resp);
	
	//  ví dụ về advance bean
	//	req.getRequestDispatcher("/views/Home2.jsp").forward(req, resp);
	
	// ví dụ về send email
    //	req.getRequestDispatcher("/views/Email.jsp").forward(req, resp);

    // ví dụ về cookies and session
       req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
}

}
