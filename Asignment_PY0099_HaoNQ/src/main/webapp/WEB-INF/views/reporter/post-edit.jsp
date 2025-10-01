<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<main class="container">
  <section class="content">
    <h1>Sửa bài #${news.id}</h1>
    <form class="form" method="post" action="${pageContext.request.contextPath}/reporter/post/edit" enctype="multipart/form-data">
      <input type="hidden" name="id" value="${news.id}">
      <label>Tiêu đề</label>
      <input name="title" value="${news.title}" required>
      <label>Loại tin</label>
      <select name="categoryId" required>
        <c:forEach var="c" items="${categories}">
          <option value="${c.id}" <c:if test="${c.id == news.categoryId}">selected</c:if>>${c.name}</option>
        </c:forEach>
      </select>
      <label>Ảnh hiện tại</label>
      <c:if test="${not empty news.image}">
        <img src="${pageContext.request.contextPath}/assets/img/${news.image}" style="max-width:240px">
      </c:if>
      <input type="file" name="image">
      <label>Trang nhất</label>
      <label class="inline"><input type="checkbox" name="home" value="true" <c:if test="${news.home}">checked</c:if>> Trang nhất</label>
      <label>Nội dung</label>
      <textarea name="content" rows="10">${news.content}</textarea>
      <div class="actions">
        <button class="btn" type="submit">Lưu</button>
        <a class="btn ghost" href="${pageContext.request.contextPath}/reporter/posts">Quay lại</a>
      </div>
    </form>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
