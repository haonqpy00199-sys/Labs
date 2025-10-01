<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculate</title>
</head>
<body>
    <h2>Máy tính đơn giản</h2>

    <c:url value="/calculate" var="cal"/>
    <form method="post">
        <input name="a" placeholder="Nhập số thứ nhất"><br><br>
        <input name="b" placeholder="Nhập số thứ hai"><br><br>
        <button formaction="${cal}/add">+</button>
        <button formaction="${cal}/sub">-</button>
    </form>

    <p style="color:blue">
        ${message}
    </p>	
</body>
</html>
