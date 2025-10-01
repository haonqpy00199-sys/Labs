<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<main class="container">
  <section class="content" style="max-width:760px; margin:0 auto;">
    <h1>Hồ sơ cá nhân</h1>
    <c:if test="${empty sessionScope.user}">
      <div class="alert">Bạn cần đăng nhập để xem trang này.</div>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
      <form class="form" method="post" action="${pageContext.request.contextPath}/profile">
        <label>Họ tên</label>
        <input name="fullname" value="${sessionScope.user.fullname}">
        <label>Email (không đổi)</label>
        <input name="email" value="${sessionScope.user.email}" readonly>
        <label>Số điện thoại</label>
        <input name="mobile" value="${sessionScope.user.mobile}">
        <div class="actions">
          <button class="btn" type="submit">Cập nhật</button>
          <a class="btn ghost" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>
      </form>
    </c:if>
  </section>
</main>

<%@ include file="layout/footer.jsp" %>
