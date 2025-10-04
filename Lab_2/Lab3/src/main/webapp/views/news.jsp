<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bản tin</title>
</head>
<body>
    <h2>Thông tin bản tin</h2>
    <ul>
        <li>
            Title: 
            <b>${fn:toUpperCase(item.title)}</b>
        </li>
        <li>
            Content: 
            <c:choose>
                <c:when test="${fn:length(item.content) > 100}">
                    ${fn:substring(item.content, 0, 100)}...
                </c:when>
                <c:otherwise>
                    ${item.content}
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</body>
</html>
