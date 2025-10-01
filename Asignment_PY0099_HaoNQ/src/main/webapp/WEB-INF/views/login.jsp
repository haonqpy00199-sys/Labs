<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<main class="container">
  <section class="content" style="max-width:560px; margin:0 auto;">
    <h1>Đăng nhập</h1>
    <c:if test="${not empty error}">
      <div class="alert error">${error}</div>
    </c:if>
    <form class="form" method="post" action="${pageContext.request.contextPath}/login">
      <label>Email</label>
      <input type="email" name="email" required>
      <label>Mật khẩu</label>
      <input type="password" name="password" required>
      <div class="actions">
        <button class="btn" type="submit">Đăng nhập</button>
        <a class="btn ghost" href="${pageContext.request.contextPath}/register">Tạo tài khoản</a>
      </div>
    </form>
  </section>
</main>

<%@ include file="layout/footer.jsp" %>
