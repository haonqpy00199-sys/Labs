<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:url var= "url" value= "/bean/advance/submit.php"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home 2</title>
</head>
<body>
<form action="${url}" method="post" enctype="multipart/form-data">

<input name="recruiteDate" placeholder="Recruitment Date?"> <br>
<input name="photo_file" type="file"> <br>
<input name="hobby" type="checkbox" value="0">  Reading
<input name="hobby" type="checkbox" value="1" >	Traveling
<input name="hobby" type="checkbox" value="2"> Music
<input name="hobby" type="checkbox" value="3"> Other
<hr>
<button> Submit</button>
</form>

</body>
</html>