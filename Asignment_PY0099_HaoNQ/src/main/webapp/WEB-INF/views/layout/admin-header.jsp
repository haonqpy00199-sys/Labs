<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản trị - NewsPortal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<header class="admin-header">
    <div class="container">
        <h1 class="logo">NewsPortal <span class="small">Admin</span></h1>
        <nav class="nav">
            <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/news-approve">Duyệt bài</a>
            <a href="${pageContext.request.contextPath}/admin/users">Người dùng</a>
            <a href="${pageContext.request.contextPath}/admin/categories">Chuyên mục</a>
            <a href="${pageContext.request.contextPath}/admin/settings">Cài đặt</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout">Đăng xuất</a>
        </nav>
    </div>
</header>
<main class="container admin-main">
