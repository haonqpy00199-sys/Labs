<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>
<jsp:include page="../layout/admin-sidebar.jsp"/>
<style>
/* ========== GIAO DIỆN CHUNG ========== */
body {
  font-family: "Roboto", "Segoe UI", Arial, sans-serif;
  background-color: #f3f4f6;
  color: #222;
  margin: 0;
  padding: 0;
}

main.container {
  max-width: 1100px;
  margin: 0 auto;
  padding: 30px 20px;
}

/* Tiêu đề */
h1 {
  font-size: 28px;
  font-weight: 700;
  color: #1a73e8;
  margin-bottom: 25px;
  border-left: 5px solid #1a73e8;
  padding-left: 10px;
}

/* ========== FORM THÊM LOẠI TIN ========== */
.form {
  background-color: #fff;
  padding: 20px 25px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
  margin-bottom: 30px;
  transition: transform 0.2s ease;
}

.form:hover {
  transform: translateY(-1px);
}

.form label {
  display: block;
  font-weight: 500;
  color: #333;
  margin-bottom: 6px;
}

.form input[name="name"] {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 15px;
  transition: border-color 0.25s;
}

.form input[name="name"]:focus {
  border-color: #1a73e8;
  outline: none;
}

/* Nút thêm */
.btn {
  background-color: #1a73e8;
  color: #fff;
  border: none;
  border-radius: 6px;
  padding: 10px 18px;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.25s, transform 0.1s;
}

.btn:hover {
  background-color: #0b5ec4;
}

.btn:active {
  transform: scale(0.97);
}

/* ========== BẢNG DANH SÁCH ========== */
.table {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.table thead {
  background: linear-gradient(90deg, #1a73e8, #3c8ce7);
  color: white;
}

.table th,
.table td {
  padding: 14px 18px;
  text-align: left;
}

.table th {
  font-weight: 600;
  letter-spacing: 0.3px;
  text-transform: uppercase;
  font-size: 14px;
}

.table tbody tr {
  transition: background-color 0.2s;
}

.table tbody tr:hover {
  background-color: #f1f6ff;
}

/* Cột hành động */
.table td.actions {
  text-align: right;
  width: 160px;
}

.actions a {
  text-decoration: none;
  font-weight: 500;
  padding: 6px 12px;
  border-radius: 6px;
  transition: all 0.25s;
}

/* Nút Sửa */
.actions a:first-child {
  background-color: #f4f4f4;
  color: #333;
}

.actions a:first-child:hover {
  background-color: #e2e2e2;
}

/* Nút Xóa */
.actions a:last-child {
  background-color: #e53935;
  color: #fff;
}

.actions a:last-child:hover {
  background-color: #c62828;
}

/* ========== BỐ CỤC GRID ========== */
.grid {
  display: grid;
  gap: 12px;
}

.actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
  align-items: center;
}

.mt-24 {
  margin-top: 24px;
}

/* ========== HIỆU ỨNG BÁO TRANG ========== */
/* Viền nhấn nhẹ, font hiện đại, không gian thoáng */
section.content {
  background-color: #fff;
  padding: 25px 30px;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  animation: fadeIn 0.3s ease;
}

/* Animation vào trang */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

</style>
<main class="container">
  <section class="content">
    <h1>Quản lý Loại tin</h1>

    <form class="form" method="post" action="${pageContext.request.contextPath}/admin/categories/create">
      <div class="grid" style="grid-template-columns:1fr 200px; gap:12px">
        <div>
          <label>Tên loại</label>
          <input name="name" required>
        </div>
        <div class="actions" style="align-items:end">
          <button class="btn" type="submit">Thêm</button>
        </div>
      </div>
    </form>

    <table class="table mt-24">
      <thead><tr><th>ID</th><th>Tên</th><th></th></tr></thead>
      <tbody>
        <c:forEach var="c" items="${categories}">
          <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td class="actions">
              <a href="${pageContext.request.contextPath}/admin/categories/edit?id=${c.id}">Sửa</a>
              <a href="${pageContext.request.contextPath}/admin/categories/delete?id=${c.id}">Xóa</a>
            </td>
          </tr>
       </c:forEach>
      </tbody>
    </table>
  </section>
</main>

<%@ include file="../layout/footer.jsp" %>
