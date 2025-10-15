
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>

<main class="container">
  <section class="content">
    <h1>Cài đặt hệ thống</h1>
    <form class="form" method="post" action="${pageContext.request.contextPath}/admin/settings/save">
      <label>Tiêu đề site</label>
      <input name="siteTitle" value="${settings.siteTitle}">
      <label>Email gửi newsletter</label>
      <input name="newsletterFrom" value="${settings.newsletterFrom}">
      <div class="actions">
        <button class="btn" type="submit">Lưu</button>
      </div>
    </form>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
