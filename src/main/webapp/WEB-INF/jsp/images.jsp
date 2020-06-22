<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Images</title>
</head>

<body>
<h2>Images</h2>

<h3>All resolutions of ${images[0].name}</h3>
<c:forEach items="${images}" var="img">
    <div style="display: inline-block;">
        <img src="content/${img.uuid}" style="max-width: 100%;">
    </div>
</c:forEach>

<p><a href="gallery">Return to the gallery</a></p></body>
</html>
