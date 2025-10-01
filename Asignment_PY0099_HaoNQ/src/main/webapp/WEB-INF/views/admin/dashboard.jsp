<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/admin-header.jsp" %>

<h2>Dashboard</h2>

<div class="grid-3 stats">
    <div class="card">
        <h3>${newsTotal}</h3>
        <p>Tổng số bài viết</p>
    </div>
    <div class="card">
        <h3>${pendingCount}</h3>
        <p>Bài chờ duyệt</p>
    </div>
    <div class="card">
        <h3>${usersTotal}</h3>
        <p>Người dùng</p>
    </div>
</div>

<section class="card">
    <h3>Chuyên mục</h3>
    <ul class="list">
        <c:forEach var="c" items="${categories}">
            <li>${c.name}</li>
        </c:forEach>
    </ul>
</section>

<%@ include file="../layout/footer.jsp" %>
