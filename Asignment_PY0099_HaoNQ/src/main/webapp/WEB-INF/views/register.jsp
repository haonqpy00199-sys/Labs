<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<main class="container">
  <section class="content" style="max-width:700px; margin:0 auto;">
    <h1>Đăng ký</h1>
    <c:if test="${not empty error}">
      <div class="alert error">${error}</div>
    </c:if>
    <form class="form" method="post" action="${pageContext.request.contextPath}/register">
      <label>Họ tên</label>
      <input type="text" name="fullname" required>
      <label>Email</label>
      <input type="email" name="email" required>
      <label>Mật khẩu</label>
      <input type="password" name="password" required>
      <label>Ngày sinh</label>
      <input type="date" name="birthday">
      <label>Giới tính</label>
      <div class="inline">
        <label class="inline"><input type="radio" name="gender" value="true"> Nam</label>
        <label class="inline"><input type="radio" name="gender" value="false"> Nữ</label>
      </div>
      <div class="actions">
        <button class="btn" type="submit">Tạo tài khoản</button>
        <a class="btn ghost" href="${pageContext.request.contextPath}/login">Đã có tài khoản</a>
      </div>
    </form>
  </section>
</main>

<%@ include file="layout/footer.jsp" %>
