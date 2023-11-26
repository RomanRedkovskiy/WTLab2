<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../navbar/navbar.jsp" />
    <h3 class="mt-4">Users</h3>

    <c:forEach var="user" items="${users}">
        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title">${user.getUsername()}</h5>
                <p class="card-text">${user.getStatus()}</p>
                <c:choose>
                    <c:when test="${user.getStatus() == 'UNBLOCKED'}">
                        <form action="${pageContext.servletContext.contextPath}/user/change-status/${user.getId()}?status=BLOCKED" method="post">
                            <button type="submit" class="btn btn-danger">Block</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.servletContext.contextPath}/user/change-status/${user.getId()}?status=UNBLOCKED" method="post">
                            <button type="submit" class="btn btn-success">Unblock</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
