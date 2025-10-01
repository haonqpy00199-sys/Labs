<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<h2>Quản lý người dùng</h2>

<c:forEach var="u" items="${users}">
    <div class="card user-row">
        <div>
            <strong>${u.fullname}</strong> | ${u.email}
            <span class="badge"><c:choose><c:when test="${u.role}">Admin</c:when><c:otherwise>Reporter</c:otherwise></c:choose></span>
            <span class="badge ${u.activated ? 'active' : 'inactive'}">
                ${u.activated ? 'Hoạt động' : 'Đã khoá'}
            </span>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/users" class="actions">
            <input type="hidden" name="id" value="${u.id}">
            <c:choose>
                <c:when test="${u.activated}">
                    <button class="btn ghost" name="action" value="deactivate">Khoá</button>
                </c:when>
                <c:otherwise>
                    <button class="btn" name="action" value="activate">Mở khoá</button>
                </c:otherwise>
            </c:choose>

            <c:if test="${u.role}">
                <button class="btn ghost" name="action" value="demote">Thành reporter</button>
            </c:if>
            <c:if test="${!u.role}">
                <button class="btn" name="action" value="promote">Thành admin</button>
            </c:if>

            <button class="btn danger" name="action" value="delete" onclick="return confirm('Xoá người dùng này?')">Xoá</button>
        </form>
    </div>
</c:forEach>

<%@ include file="../layout/footer.jsp" %>
