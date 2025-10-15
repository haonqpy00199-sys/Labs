<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title><c:out
		value="${pageTitle != null ? pageTitle : 'NewsPortal'}" /></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
	<header>
		<div class="container nav">
			<a class="brand" href="${pageContext.request.contextPath}/home">
				<span class="dot"></span> <span>NewsPortal</span>
			</a>
			<nav class="menu">
				<a href="${pageContext.request.contextPath}/home">Trang chủ</a>
				<c:forEach var="cata" items="${categories}">
					<a href="${pageContext.request.contextPath}/category?id=${cata.id}">
						${cata.name} </a>
				</c:forEach>
				<a href="${pageContext.request.contextPath}/search">Tìm kiếm</a>
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<c:if test="${sessionScope.user.role}">
							<a href="${pageContext.request.contextPath}/admin/dashboard">Quản
								trị</a>
						</c:if>
						<c:if test="${!sessionScope.user.role}">
							<a href="${pageContext.request.contextPath}/reporter/dashboard">Bảng
								điều khiển</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
						<a href="${pageContext.request.contextPath}/register">Đăng ký</a>
					</c:otherwise>
				</c:choose>
			</nav>
		</div>
	</header>