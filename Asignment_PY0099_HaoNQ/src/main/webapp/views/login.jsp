<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<style>
/* === FORM LOGIN === */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 70vh;
  background-color: #f5f7fa;
  padding: 20px;
}

.content {
  background: #fff;
  padding: 40px 50px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.content h1 {
  text-align: center;
  color: #0078D7;
  margin-bottom: 25px;
  font-size: 28px;
}

.form label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
  color: #333;
}

.form input[type="email"],
.form input[type="password"] {
  width: 100%;
  padding: 12px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 15px;
  transition: border-color 0.2s;
}

.form input:focus {
  border-color: #0078D7;
  outline: none;
}

/* === Nút hành động === */
.actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

.btn {
  flex: 1;
  background-color: #0078D7;
  color: white;
  border: none;
  padding: 12px 0;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 600;
  transition: background 0.3s;
  text-align: center;
  text-decoration: none;
}

.btn:hover {
  background-color: #005fa3;
}

.btn.ghost {
  background-color: #fff;
  border: 1px solid #0078D7;
  color: #0078D7;
}

.btn.ghost:hover {
  background-color: #0078D7;
  color: white;
}

/* === Thông báo lỗi === */
.alert.error {
  background: #ffe5e5;
  color: #d90429;
  border-left: 5px solid #d90429;
  padding: 10px 15px;
  border-radius: 5px;
  margin-bottom: 20px;
  font-size: 15px;
}
</style>


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
