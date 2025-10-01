<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>




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
