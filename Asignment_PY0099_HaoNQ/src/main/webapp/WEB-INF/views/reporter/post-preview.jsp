<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<main class="container">
  <section class="content">
    <h1>Xem trước: ${news.title}</h1>
    <div class="meta">Tác giả: ${news.author} · Loại: ${news.categoryId}</div>
    <c:if test="${not empty news.image}">
      <img class="mt-24" src="${pageContext.request.contextPath}/assets/img/${news.image}">
    </c:if>
    <div class="mt-24">${news.content}</div>
    <div class="actions mt-24">
      <a class="btn" href="${pageContext.request.contextPath}/reporter/post/edit?id=${news.id}">Sửa</a>
      <a class="btn ghost" href="${pageContext.request.contextPath}/reporter/posts">Quay lại</a>
    </div>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
