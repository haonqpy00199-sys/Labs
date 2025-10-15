<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BeanSimple</title>
</head>
<body>
<p> Name: ${staffBean.getFullname()} </p>
<p> Age: ${staffBean.getAge()} </p>
<p> Gender: ${staffBean.isGender()} </p>
<p> Country: ${staffBean.getCountry()} </p>
</body>
</html>