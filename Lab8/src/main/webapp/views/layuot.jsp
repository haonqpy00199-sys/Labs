<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" scope="request" />
<fmt:setBundle basename="global" scope="request"  />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FPT Polytechnic</title>
</head>
<body>
	<header>
		<h1>FPT POLYTECHNIC</h1>
	</header>
	<!--  Bài 1
	<nav>
		<hr>
		<c:url value="/home" var="path" />
		<a href="${path}/index">Home</a> 
		<a href="${path}/about">About Us</a>
		<a href="${path}/contact">Contact Us</a>
		<hr>
	</nav>
 -->
	<c:url value="/home" var="home" />
	<a href="${home}/index"><fmt:message key="menu.home" /></a>
	<a href="${home}/about"><fmt:message key="menu.about" /></a>
	<a href="${home}/contact"><fmt:message key="menu.contact" /> </a> ||
	<a href=?lang=vi> Tiếng Việt</a>
	<a href=?lang=en> English</a>
	<main>
		<jsp:include page="${view}" />
	</main>
	<footer>
		<hr>
		&copy; 2024 by FPT Polytechnic. All rights reserved.
	</footer>
</body>
</html>