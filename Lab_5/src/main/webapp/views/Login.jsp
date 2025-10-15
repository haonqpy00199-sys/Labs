<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c"%>
<c:url var="url" value="/login" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>Login Form</h2>

	<!-- Form đăng nhập -->
	<form action="${url}" method="post">
		<input name="username" value="${username}"> <br> <input
			name="password" value="${password}"> <br> <input
			type="checkbox" name="rememberMe" > Remember me?
		<hr>
		<button>Login</button>
	</form>

	<h3 style="color: blue;">${message}</h3>
	<c:if test="${not empty sessionScope.sessionUsername}">
		<p>Welcome, ${sessionScope.sessionUsername}!</p>
	</c:if>
</body>
</html>