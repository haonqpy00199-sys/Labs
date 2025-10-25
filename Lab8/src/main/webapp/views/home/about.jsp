<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="home" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
</head>
<body>
	<!-- 
	Bài 1 
	<h1>GIỚI THIỆU</h1>
-->

	<h2>
		<fmt:message key="about.title" />
	</h2>
</body>
</html>