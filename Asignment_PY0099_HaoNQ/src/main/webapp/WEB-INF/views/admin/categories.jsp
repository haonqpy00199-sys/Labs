<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<main class="container">
  <section class="content">
    <h1>Quản lý Loại tin</h1>

    <form class="form" method="post" action="${pageContext.request.contextPath}/admin/categories/create">
      <div class="grid" style="grid-template-columns:1fr 200px; gap:12px">
        <div>
          <label>Tên loại</label>
          <input name="name" required>
        </div>
        <div class="actions" style="align-items:end">
          <button class="btn" type="submit">Thêm</button>
        </div>
      </div>
    </form>

    <table class="table mt-24">
      <thead><tr><th>ID</th><th>Tên</th><th></th></tr></thead>
      <tbody>
        <c:forEach var="c" items="${categories}">
          <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td class="actions">
              <a href="${pageContext.request.contextPath}/admin/categories/edit?id=${c.id}">Sửa</a>
              <a href="${pageContext.request.contextPath}/admin/categories/delete?id=${c.id}">Xóa</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
