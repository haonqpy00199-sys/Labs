<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layout/header.jsp" %>

<main class="container">
  <h1>Đăng bài mới</h1>

  <form method="post" action="${pageContext.request.contextPath}/reporter/post-create" enctype="multipart/form-data" class="card">
    <label>Tiêu đề</label>
    <input type="text" name="title" required class="input">

    <label>Chuyên mục</label>
    <select name="categoryId" class="input" required>
      <c:forEach var="c" items="${categories}">
        <option value="${c.id}">${c.name}</option>
      </c:forEach>
    </select>

    <label>Ảnh đại diện (thumbnail) (tùy chọn)</label>
    <input type="file" name="thumbnail" accept="image/*" class="input">

    <label>Nội dung</label>
    <textarea id="content" name="content" rows="15" class="input"></textarea>

    <label class="row mt-12">
      <input type="checkbox" name="home" value="1"> Đưa lên mục Trang chủ
    </label>

    <button class="btn mt-12">Đăng bài</button>
  </form>
</main>

<!-- CKEditor5 classic build -->
<script src="https://cdn.ckeditor.com/ckeditor5/41.4.1/classic/ckeditor.js"></script>
<script>
  ClassicEditor.create(document.querySelector('#content'), {
    simpleUpload: {
      uploadUrl: '${pageContext.request.contextPath}/upload-image'
      // Nếu cần headers auth thì thêm ở đây.
    },
    toolbar: [
      'heading','|','bold','italic','underline','link','bulletedList','numberedList',
      '|','blockQuote','insertTable','mediaEmbed','undo','redo','imageUpload'
    ]
  }).catch(console.error);
</script>

<%@ include file="../layout/footer.jsp" %>
