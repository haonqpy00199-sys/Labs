<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Calculate</title>
</head>
<body>
    <form method="post">
        <input name="a"><br>
        <input name="b"><br>
        <button formaction="${pageContext.request.contextPath}/calculate/add">+</button>
        <button formaction="${pageContext.request.contextPath}/calculate/sub">-</button>
    </form>
    <p>${message}</p>
</body>
</html>