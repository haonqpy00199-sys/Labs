<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<main class="container grid">
  <section class="content">
    <h1>Tìm kiếm</h1>
    <form class="searchbar" action="${pageContext.request.contextPath}/search" method="get">
      <input type="text" name="q" value="${param.q}" placeholder="Nhập từ khóa...">
      <button class="btn" type="submit">Tìm</button>
    </form>

    <c:if test="${not empty q}">
      <h2>Kết quả cho "<c:out value="${q}"/>":</h2>
    </c:if>

    <ul class="list mt-24">
      <c:forEach var="n" items="${results}">
        <li><a href="${pageContext.request.contextPath}/news/${n.id}">${n.title}</a></li>
      </c:forEach>
      <c:if test="${empty results}">
        <li>Không tìm thấy bài viết phù hợp.</li>
      </c:if>
    </ul>
  </section>

  <aside><jsp:include page="layout/sidebar.jsp"/></aside>
</main>

<%@ include file="layout/footer.jsp" %>
