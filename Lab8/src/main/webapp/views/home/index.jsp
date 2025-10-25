<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="home" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<!--  
	Bài 1
	<h1>TRANG CHỦ</h1> -->

	<h2>
		<fmt:message key="index.title" />
	</h2>
</body>
</html>