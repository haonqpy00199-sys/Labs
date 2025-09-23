<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page JSP</title>
</head>
<body>
    <h2>${message}</h2>

    <!-- Bao hàm user-info.jsp -->
    <jsp:include page="user-info.jsp"/>
</body>
</html>