<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/admin-header.jsp" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<h2>Duyệt bài viết</h2>

<c:if test="${empty pendingList}">
    <p>Không có bài viết chờ duyệt.</p>
</c:if>

<c:forEach var="n" items="${pendingList}">
    <div class="card article">
        <h3>${n.title}</h3>
        <small>Đăng bởi: ${n.author} - ${n.postedDate}</small>
        <p>${fn:substring(n.content,0,150)}...</p>

        <form method="post" action="${pageContext.request.contextPath}/admin/news-approve" class="actions">
            <input type="hidden" name="id" value="${n.id}">
            <button class="btn" name="action" value="approve">Duyệt</button>
            <button class="btn ghost" name="action" value="home-on">Trang chủ</button>
            <button class="btn danger" name="action" value="reject" onclick="return confirm('Xoá bài này?')">Xoá</button>
        </form>
    </div>
</c:forEach>

<%@ include file="../layout/footer.jsp" %>
