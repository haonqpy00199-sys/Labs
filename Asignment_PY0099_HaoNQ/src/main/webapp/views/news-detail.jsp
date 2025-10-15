<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<style>
/* === TRANG CHI TIẾT BÀI VIẾT === */

.container.grid {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 30px;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* --- Khu nội dung bài viết --- */
.content {
  background: #fff;
  padding: 30px 40px;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.08);
}

.content h1 {
  color: #0078D7;
  font-size: 30px;
  margin-bottom: 10px;
}

.meta {
  color: #777;
  font-size: 15px;
  margin-bottom: 20px;
}

.content img {
  width: 100%;
  height: auto;
  border-radius: 8px;
  margin: 20px 0;
  box-shadow: 0 1px 5px rgba(0,0,0,0.1);
}

/* --- Nội dung bài viết --- */
.article-body {
  color: #333;
  line-height: 1.8;
  font-size: 17px;
  margin-bottom: 25px;
}

.article-body p {
  margin-bottom: 15px;
}

.article-body h2,
.article-body h3 {
  color: #005fa3;
  margin-top: 25px;
}

/* --- Danh sách bài liên quan --- */
.content hr {
  border: none;
  border-top: 2px solid #eee;
  margin: 40px 0 20px;
}

.content h2 {
  font-size: 22px;
  color: #0078D7;
  margin-bottom: 15px;
}

.list {
  list-style: none;
  padding-left: 0;
}

.list li {
  padding: 8px 0;
  border-bottom: 1px solid #eee;
}

.list li:last-child {
  border-bottom: none;
}

.list a {
  color: #333;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.2s;
}

.list a:hover {
  color: #0078D7;
}

/* --- Sidebar --- */
aside {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* --- Responsive --- */
@media (max-width: 900px) {
  .container.grid {
    grid-template-columns: 1fr;
  }
  .content {
    padding: 20px;
  }
}
</style>


<main class="container grid">
  <section class="content">
    <h1>${news.title}</h1>
    <div class="meta">Đăng bởi: <c:out value="${news.author}"/> · ${news.viewCount} lượt xem</div>
    <c:if test="${not empty news.image}">
    <c:choose>
    <c:when test="${fn:startsWith(news.image, '/') || fn:startsWith(news.image, 'http')}">
        <img class="mt-24" src="${news.image}" alt="${news.title}">
    </c:when>
    <c:otherwise>
        <img class="mt-24" src="${pageContext.request.contextPath}/${news.image}" alt="${news.title}">
    </c:otherwise>
</c:choose>
</c:if>

    <div class="mt-24 article-body">
  <c:out value="${news.content}" escapeXml="false"/>
</div>

    <hr/>
    <h2>Bài cùng loại</h2>
    <ul class="list">
      <c:forEach var="rel" items="${related}">
        <li><a href="${pageContext.request.contextPath}/news/${rel.id}">${rel.title}</a></li>
      </c:forEach>
    </ul>
  </section>

  <aside>
    <jsp:include page="layout/sidebar.jsp"/>
  </aside>
</main>

<%@ include file="layout/footer.jsp" %>
