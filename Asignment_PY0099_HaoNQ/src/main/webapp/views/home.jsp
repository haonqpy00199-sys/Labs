<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<style>
/* Tổng thể trang */
body {
  font-family: "Segoe UI", Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #fafafa;
  color: #333;
}

.home-wrap {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* Lưới chính: 2 cột */
.home-grid {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 30px;
}

/* Khu vực nội dung chính (trái) */
.home-main .content {
  background: #fff;
  padding: 20px 25px;
  border-radius: 8px;
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.08);
}

/* Mỗi bài viết */
.news-item {
  border-bottom: 1px solid #ddd;
  padding: 15px 0;
}

.news-item:last-child {
  border-bottom: none;
}

.news-item h3 {
  margin: 0;
  font-size: 22px;
  color: #0078D7;
}

.news-item h3 a {
  text-decoration: none;
  color: #0078D7;
  transition: color 0.2s;
}

.news-item h3 a:hover {
  color: #0056a3;
}

.news-item p {
  margin: 8px 0;
  color: #444;
  line-height: 1.5;
}

.news-item small {
  display: block;
  color: #777;
  font-size: 13px;
}

/* Cột bên phải */
aside {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* Khối panel chung */
.panel {
  background: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.08);
}

.panel-h {
  font-weight: bold;
  padding: 10px 15px;
  color: #fff;
  font-size: 16px;
}

.panel-h.yellow {
  background: #f5b301;
}

.panel-h.gray {
  background: #4a4a4a;
}

.panel-h.green {
  background: #1c9d5a;
}

/* Danh sách bên trong panel */
.panel-b {
  padding: 10px 15px;
}

.panel-b ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.panel-b li {
  padding: 8px 0;
  border-bottom: 1px solid #eee;
  font-size: 15px;
}

.panel-b li:last-child {
  border-bottom: none;
}

.panel-b a {
  text-decoration: none;
  color: #333;
}

.panel-b a:hover {
  color: #0078D7;
}

/* Newsletter */
.panel-b form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.panel-b input[type="email"] {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.panel-b button {
  background-color: #0078D7;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.2s;
}

.panel-b button:hover {
  background-color: #005fa3;
}

/* Responsive cho điện thoại */
@media (max-width: 900px) {
  .home-grid {
    grid-template-columns: 1fr;
  }
  aside {
    order: -1;
  }
}
</style>



<div class="home-wrap">
  
  <div class="home-grid">

    <!-- CỘT TRÁI: THAY ĐỔI THEO TRANG -->
    <section class="home-main">
      <!-- Nếu muốn hiển thị bài Trang Nhất, bạn thay nội dung bên dưới -->
      <div class="content">
    <c:if test="${empty approvedNews}">
        <p>Chưa có bài viết nào được duyệt.</p>
    </c:if>
    <c:forEach var="n" items="${approvedNews}">
        <div class="news-item">
            <h3>
                <a href="news-detail?id=${n.id}">
                    ${n.title}
                </a>
            </h3>
            <p>${fn:substring(n.content, 0, 200)}...</p>
            <small>Đăng ngày: ${n.postedDate}</small>
        </div>
    </c:forEach>
</div>

    </section>

    <!-- CỘT PHẢI: 3 KHỐI + NEWSLETTER -->
    <aside>

      <!-- 5 bản tin được xem nhiều -->
      <div class="panel">
        <div class="panel-h yellow">5 bản tin được xem nhiều</div>
        <div class="panel-b">
          <ul>
            <c:forEach var="n" items="${hotList}" varStatus="st">
              <li>
                <a href="${pageContext.request.contextPath}/news/${n.id}">
                  <c:out value="${st.index + 1}"/>. ${n.title}
                </a>
              </li>
            </c:forEach>
            <c:if test="${empty hotList}">
              <li>Chưa có dữ liệu.</li>
            </c:if>
          </ul>
        </div>
      </div>

      <!-- 5 bản tin mới nhất -->
      <div class="panel">
        <div class="panel-h gray">5 bản tin mới nhất</div>
        <div class="panel-b">
          <ul>
            <c:forEach var="n" items="${newList}" varStatus="st">
              <li>
                <a href="${pageContext.request.contextPath}/news/${n.id}">
                  <c:out value="${st.index + 1}"/>. ${n.title}
                </a>
              </li>
            </c:forEach>
            <c:if test="${empty newList}">
              <li>Chưa có dữ liệu.</li>
            </c:if>
          </ul>
        </div>
      </div>

      <!-- 5 bản tin bạn đã xem -->
      <div class="panel">
        <div class="panel-h green">5 bản tin bạn đã xem</div>
        <div class="panel-b">
          <ul>
            <c:forEach var="n" items="${recentList}" varStatus="st">
              <li>
                <a href="${pageContext.request.contextPath}/news/${n.id}">
                  <c:out value="${st.index + 1}"/>. ${n.title}
                </a>
              </li>
            </c:forEach>
            <c:if test="${empty recentList}">
              <li>Chưa có lịch sử xem.</li>
            </c:if>
          </ul>
        </div>
      </div>

      <!-- Newsletter -->
      <div class="panel">
        <div class="panel-b">
          <form method="post" action="${pageContext.request.contextPath}/newsletter/subscribe">
            <input type="email" name="email" placeholder="Nhập Email đăng ký" required>
            <button type="submit">Đăng ký</button>
          </form>
        </div>
      </div>

    </aside>
  </div>
</div>

<%@ include file="layout/footer.jsp" %>
