<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<main class="container">
  <section class="content">
    <div class="actions">
      <a class="btn" href="${pageContext.request.contextPath}/reporter/post/create">Viết bài mới</a>
    </div>
    <table class="table mt-24">
      <thead>
        <tr><th>ID</th><th>Tiêu đề</th><th>Loại</th><th>Ngày đăng</th><th>Trạng thái</th><th></th></tr>
      </thead>
      <tbody>
        <c:forEach var="n" items="${posts}">
          <tr>
            <td>${n.id}</td>
            <td>${n.title}</td>
            <td>${n.categoryId}</td>
            <td>${n.postedDate}</td>
            <td><span class="chip"><c:out value="${n.approved ? 'Đã duyệt' : 'Chờ duyệt'}"/></span></td>
            <td class="actions">
              <a href="${pageContext.request.contextPath}/reporter/post/edit?id=${n.id}">Sửa</a>
              <a href="${pageContext.request.contextPath}/reporter/post/preview?id=${n.id}">Xem</a>
              <a href="${pageContext.request.contextPath}/reporter/post/delete?id=${n.id}">Xóa</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
