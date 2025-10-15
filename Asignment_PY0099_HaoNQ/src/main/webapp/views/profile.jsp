<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<style>
/* === TRANG HỒ SƠ CÁ NHÂN === */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 75vh;
  background-color: #f5f7fa;
  padding: 20px;
}

.content {
  background: #fff;
  padding: 40px 50px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  width: 100%;
  max-width: 760px;
}

.content h1 {
  text-align: center;
  color: #0078D7;
  margin-bottom: 30px;
  font-size: 28px;
}

/* === THÔNG BÁO === */
.alert {
  background: #fffae6;
  color: #8a6d3b;
  border-left: 5px solid #f0ad4e;
  padding: 10px 15px;
  border-radius: 6px;
  margin-bottom: 20px;
  text-align: center;
  font-size: 15px;
}

/* === FORM === */
.form label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
  color: #333;
}

.form input {
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

.form input[readonly] {
  background-color: #f1f1f1;
  color: #666;
  cursor: not-allowed;
}

/* === NÚT === */
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
  color: #fff;
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
