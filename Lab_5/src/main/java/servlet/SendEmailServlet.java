package servlet;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sendemail")
public class SendEmailServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
	    // dữ liệu truyền từ Front end, từ Email.jsp
		String to = req.getParameter("to");
		String cc= req.getParameter("cc");
		String bcc= req.getParameter("bcc");
		String subject = req.getParameter("subject");
		String body= req.getParameter("body");
		
		// tên email và mật khẩu của người gủi
		final String from = "your email";
	    final String password = "your generated app password";

// Hướng dẫn lấy password
//STEP 1: Enable 2-Step Verification (if not already enabled)
//Go to: https://myaccount.google.com/security
//Scroll down to “Signing in to Google”
//Click 2-Step Verification
//Follow the instructions to turn it on (you’ll need your phone for SMS or Google Authenticator)
//This is required to access the App Passwords feature.

//STEP 2: Access App Passwords
//Once 2-Step Verification is enabled:
//Go to: https://myaccount.google.com/apppasswords
//Sign in again if prompted

// STEP 3: Create an App Password
//Enter a name like:
//JavaMail SMTP
//Click Generate

// STEP 4: Copy the App Password
//A yellow box will appear with a 16-character password, like:
//abcd efgh ijkl mnop
//Copy this exactly (without spaces) into your Java code:
//final String from = "loptruon9codon9@gmail.com";
//final String password = "abcdefghijklnop"; // Use the generated app password
	    
	    // Thông số kết nói Smtp Server
	    Properties props = new Properties();
	    props.setProperty("mail.smtp.auth", "true");
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.smtp.host", "smtp.gmail.com");
	    props.setProperty("mail.smtp.port", "587");
        
        // Kết nối Smtp Server
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        
        // Tạo message
        try {
        	Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		    message.setSubject(subject);
		    message.setText(body);
		    Transport.send(message);
		    resp.getWriter().write("Email sent successfully!"); 
		} 
        catch (MessagingException e) {
			//e.printStackTrace();
			resp.getWriter().write("Error sending email: " + e.getMessage());
		}
    
	}

}