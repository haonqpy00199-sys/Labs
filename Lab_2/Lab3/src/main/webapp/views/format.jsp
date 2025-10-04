<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Countries List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #333;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Danh sách quốc gia</h2>

    <table>
        <thead>
            <tr>
                <th>No.</th>
                <th>Id</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ct" items="${countries}" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${ct.id}</td>
                    <td>${ct.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
