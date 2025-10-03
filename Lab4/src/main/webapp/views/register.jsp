<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký</title>
</head>
<body>
    <h2>Đăng Ký</h2>
    <form action="${pageContext.request.contextPath}/register" method="post">
        Tên đăng nhập: <input type="text" name="username"><br>
        Mật khẩu: <input type="password" name="password"><br>

        Giới tính:
        <input type="radio" name="gender" value="Nam" checked> Nam
        <input type="radio" name="gender" value="Nữ"> Nữ<br>

        <input type="checkbox" name="married" value="true"> Đã có gia đình?<br>

        Quốc tịch:
        <select name="country">
            <option value="United States">United States</option>
            <option value="Vietnam">Vietnam</option>
        </select><br>

        Sở thích:
        <input type="checkbox" name="hobby" value="Đọc sách"> Đọc sách
        <input type="checkbox" name="hobby" value="Du lịch"> Du lịch
        <input type="checkbox" name="hobby" value="Âm nhạc"> Âm nhạc
        <input type="checkbox" name="hobby" value="Khác"> Khác<br>

        Ghi chú:<br>
        <textarea name="note" rows="4" cols="30"></textarea><br><br>

        <input type="submit" value="Đăng ký">
    </form>
    <p>${message}</p>
</body>