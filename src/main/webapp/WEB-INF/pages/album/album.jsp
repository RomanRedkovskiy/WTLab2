<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Album</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../navbar/navbar.jsp" />

    <div class="mt-3 text-center">
        <c:if test="${isAdmin}">
            <a href="${pageContext.servletContext.contextPath}/album/edit/${album.getId()}"
               class="btn btn-primary">Edit</a>
        </c:if>
        <h1 class="mt-3">${album.getTitle()}</h1>
        <img src="${album.getImageUrl()}" alt="Image not found" class="img-fluid rounded mt-3">
    </div>

    <div class="mt-3">
        <p class="mt-3">Description : ${album.getDescription()}</p>
        <p class="mt-2">Release Date: ${album.getReleaseDate()}</p>
    </div>

    <form action="${pageContext.servletContext.contextPath}/comment" method="post" class="mt-3">
        <div class="form-row">
            <div class="col-md-8">
                <div class="form-group">
                    <label for="message">Comment:</label>
                    <input type="text" name="message" id="message" class="form-control">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <input type="hidden" name="albumId" value="${album.getId()}">
                </div>
            </div>
            <div class="col-md-4 mt-2">
                <div class="form-group">
                    <input type="submit" value="Create Comment" class="btn btn-primary btn-block">
                </div>
            </div>
        </div>
    </form>

    <c:forEach var="comment" items="${comments}">
        <div class="mt-4">
            <h3>${comment.getPublisherUsername()}</h3>
            <c:if test="${comment.isUserThePublisher()}">
                <form action="${pageContext.servletContext.contextPath}/comment/edit/${comment.getId()}"
                      method="post" class="form-inline mt-2">
                    <div class="form-group">
                        <input type="text" name="message" value="${comment.getMessage()}" class="form-control">
                    </div>
                    <input type="submit" value="Edit" class="btn btn-primary ml-2">
                </form>
                <form action="${pageContext.servletContext.contextPath}/comment/delete/${comment.getId()}"
                      method="post" class="form-inline mt-2">
                    <input type="submit" value="Delete" class="btn btn-danger ml-2">
                </form>
            </c:if>
            <c:if test="${!comment.isUserThePublisher()}">
                <p class="mt-2">${comment.getMessage()}</p>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>
</html>

