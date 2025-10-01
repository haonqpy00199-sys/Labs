<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>


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
