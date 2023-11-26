<%--
  Created by IntelliJ IDEA.
  User: gg
  Date: 19.11.2023
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container text-center">
    <h1 class="display-4">Your haven't authorised yet</h1>
    <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/login">Login</a>
    <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/register">Registration</a>
</div>
</body>
</html>
