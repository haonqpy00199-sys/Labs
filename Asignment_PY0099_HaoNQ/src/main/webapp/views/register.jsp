<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<style>
/* === TRANG ĐĂNG KÝ === */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  background-color: #f5f7fa;
  padding: 20px;
}

.content {
  background: #fff;
  padding: 40px 50px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  width: 100%;
  max-width: 700px;
}

.content h1 {
  text-align: center;
  color: #0078D7;
  margin-bottom: 30px;
  font-size: 28px;
}

/* === THÔNG BÁO LỖI === */
.alert.error {
  background: #ffe5e5;
  color: #d90429;
  border-left: 5px solid #d90429;
  padding: 10px 15px;
  border-radius: 5px;
  margin-bottom: 20px;
  font-size: 15px;
}

/* === FORM === */
.form label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
  color: #333;
}

.form input[type="text"],
.form input[type="email"],
.form input[type="password"],
.form input[type="date"] {
  width: 100%;
  padding: 12px;
  margin-bottom: 18px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 15px;
  transition: border-color 0.2s;
}

.form input:focus {
  border-color: #0078D7;
  outline: none;
}

/* === RADIO (Giới tính) === */
.inline {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 20px;
}

.inline label.inline {
  font-weight: 500;
  color: #333;
  display: flex;
  align-items: center;
  gap: 5px;
}

/* === NÚT HÀNH ĐỘNG === */
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

/* === Responsive === */
@media (max-width: 600px) {
  .content {
    padding: 25px 30px;
  }
  .actions {
    flex-direction: column;
  }
  .btn {
    width: 100%;
  }
}
</style>


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
