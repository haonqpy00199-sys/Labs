<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Ký</title>
</head>
<body>
    <h2>Đăng Ký</h2>
    <form action="dang-ky" method="post">
        Tên đăng nhập: <input type="text" name="username"><br>
        Mật khẩu: <input type="password" name="password"><br>

        Giới tính:
        <input type="radio" name="gender" value="Nam"> Nam
        <input type="radio" name="gender" value="Nữ"> Nữ <br>

        <input type="checkbox" name="married" value="yes"> Đã có gia đình?<br>

        Quốc tịch:
        <select name="country">
            <option>United States</option>
            <option>Vietnam</option>
            <option>Japan</option>
        </select><br>

        Sở thích:
        <input type="checkbox" name="hobbies" value="Đọc sách"> Đọc sách
        <input type="checkbox" name="hobbies" value="Du lịch"> Du lịch
        <input type="checkbox" name="hobbies" value="Âm nhạc"> Âm nhạc
        <input type="checkbox" name="hobbies" value="Khác"> Khác <br>

        <textarea name="notes" rows="3" cols="30"></textarea><br>

        <button>Đăng ký</button>
    </form>
</body>
</html>
