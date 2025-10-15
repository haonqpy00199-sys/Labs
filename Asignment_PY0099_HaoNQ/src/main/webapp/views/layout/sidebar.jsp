<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<aside class="sidebar">
  <h3>Chuyên mục</h3>
  <ul>
    <c:forEach var="cata" items="${categories}">
      <li><a href="${pageContext.request.contextPath}/category?id=${cata.id}">${cata.name}</a></li>
    </c:forEach>
  </ul>
  <hr/>
  <h3>Liên hệ</h3>
  <div class="meta">contact@newsportal.local</div>
</aside>
