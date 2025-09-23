<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FPT </title>
</head>
<body>
<h1> Trang page</h1>
<p> ${message} </p>
<p>  ${now.date} </p>

<!-- Bao gồm file user-info.jsp -->
<h2>${message}</h2>
<jsp:include page="user-info.jsp"/>


</body>
</html>