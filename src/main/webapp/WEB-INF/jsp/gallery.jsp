<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gallery</title>
</head>

<body>
<h2>Gallery</h2>

<h3>Upload an image</h3>
<form action="gallery" enctype="multipart/form-data" method="POST">
    <input type="file" name="file" accept="image/*">
    <input type="submit" value="Upload">
</form>

<h3>Uploaded images (click to view all sizes)</h3>
<c:forEach items="${images}" var="img">
    <div style="display: inline-block; text-align: center; border: 1px solid grey; padding: 6px;">
        <a href="images?name=${img.name}"><img src="content/${img.uuid}"></a><br>
        ${img.name}
    </div>
</c:forEach>

<p><a href="files">View all files</a></p>
</body>
</html>
