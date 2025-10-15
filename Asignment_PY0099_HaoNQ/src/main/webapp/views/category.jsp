<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<main class="container grid">
  <section class="content">
    <h1>Chuyên mục: <c:out value="${currentCategory.name}"/></h1>
    <div class="list-posts">
      <c:forEach var="n" items="${news}">
        <article class="card">
          <a href="${pageContext.request.contextPath}/news/${n.id}">
            <img src="${pageContext.request.contextPath}/assets/img/${empty n.image ? 'sample.jpg' : n.image}" alt="${n.title}">
          </a>
          <div class="body">
            <h3><a href="${pageContext.request.contextPath}/news/${n.id}">${n.title}</a></h3>
            <div class="meta">${n.viewCount} lượt xem</div>
          </div>
        </article>
      </c:forEach>
    </div>
  </section>

  <aside>
    <jsp:include page="layout/sidebar.jsp"/>
  </aside>
</main>

<%@ include file="layout/footer.jsp" %>
