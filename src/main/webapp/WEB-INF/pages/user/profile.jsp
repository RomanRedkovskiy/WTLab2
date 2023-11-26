
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../navbar/navbar.jsp" />
    <form class="mt-4" method="post" action="${pageContext.servletContext.contextPath}/profile">
        <div class="form-group">
            <label> Username: <strong>${user.getUsername()}</strong></label>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" name="email" id="email" value="${user.getEmail()}">
        </div>
        <div class="form-group">
            <label for="bio">Bio:</label>
            <input type="text" class="form-control" name="bio" id="bio" value="${user.getBio()}">
        </div>
        <button type="submit" class="btn btn-primary">Edit</button>
    </form>
</div>
</body>
</html>
