<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File</title>
</head>
<body>
    <h2>Upload File</h2>

    <c:url value="/upload" var="url"/>
    <form action="${url}" method="post" enctype="multipart/form-data">
        <input name="photo" type="file"><br>
        <button>Upload</button>
    </form>

    <p style="color:green">${message}</p>
</body>
</html>
