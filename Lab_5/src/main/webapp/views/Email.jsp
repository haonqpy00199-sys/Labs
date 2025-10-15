<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email</title>
   <style>
        body {
            font-family: Arial, sans-serif;
            background: #f7f9fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        form {
            background: white;
            padding: 25px;
            border-radius: 12px;
            width: 400px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        input[type=text],input[type=email], textarea {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        textarea {
            height: 120px;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
        }
        .msg {
            text-align: center;
            color: #0066cc;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<form action="sendemail" method="post">
    <h2>Send Email</h2>
    
    <label>To:</label>
    <input type="email" name="to" required>

    <label>CC:</label>
    <input type="email" name="cc">

    <label>BCC:</label>
    <input type="email" name="bcc">

    <label>Subject:</label>
    <input type="text" name="subject" required>

    <label>Message:</label>
    <textarea name="body" required></textarea>

    <button type="submit">Send Email</button>

    <div class="msg">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </div>
</form>
</body>
</html>