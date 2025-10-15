<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:url var= "url" value="/bean/simple/submit.php"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
<p> Hello Vietnam</p>
</body>
<form  action="${url}" method="post">
    <input name="fullname" placeholder="Fullname?"><br>
    <input name="age" placeholder="Age?"><br>
    <input name="gender" type="radio" value="Male"> Male
    <input name="gender" type="radio" value="Female"> Female
    <br>
    <select name="country">
        <option value="VN">Việt Nam</option>
        <option value="US">United States</option>
    </select><br>
    <input name="salary" placeholder="Salary?"><br>
    <button type="submit">Submit</button>
</form>

</html>