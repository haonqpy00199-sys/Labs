<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<main class="container">
  <section class="content">
    <h1>Dashboard Phóng viên</h1>
    <div class="grid" style="grid-template-columns:repeat(3,1fr); gap:18px">
      <div class="content"><h3>Tổng bài viết</h3><div class="h2">${stats.total}</div></div>
      <div class="content"><h3>Chờ duyệt</h3><div class="h2">${stats.pending}</div></div>
      <div class="content"><h3>Đã duyệt</h3><div class="h2">${stats.approved}</div></div>
      <a class="btn" href="${pageContext.request.contextPath}/reporter/post-create">Đăng bài mới</a>
      
    </div>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
