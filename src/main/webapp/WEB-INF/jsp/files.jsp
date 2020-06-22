<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>File storage</title>
</head>

<body>
<h2>File storage</h2>

<h3>Upload a file</h3>
<form action="files" enctype="multipart/form-data" method="POST">
  <input type="file" name="file" accept="*/*">
  <input type="submit" value="Upload">
</form>

<h3>Uploaded files (click to download)</h3>
<ol>
  <c:forEach items="${files}" var="file">
    <li>
      <a href="content/${file.uuid}?dl">${file.name}</a>:
      ${file.mimeType}
      (<fmt:formatNumber value="${file.size}"/> bytes)
    </li>
  </c:forEach>
</ol>

<p><a href="gallery">View only images</a></p>
</body>
</html>
