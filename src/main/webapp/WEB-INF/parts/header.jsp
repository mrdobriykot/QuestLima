
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">

</head>

<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" >
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Fixed navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">Disabled</a>
                </li>

                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a class="nav-link active" aria-current="page" href="profile">Profile</a></li>
                        <li><a class="nav-link active" aria-current="page" href="logout">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a class="nav-link active" aria-current="page" href="logins">Login</a></li>
                        <li><a class="nav-link active" aria-current="page" href="signup">Sign-up</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>

<%--            <form class="d-flex" role="search">--%>
<%--                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">--%>
<%--                <button class="btn btn-outline-success" type="submit">Search</button>--%>
<%--            </form>--%>
<%--            <ul class="navbar-nav me-auto mb-2 mb-md-0">--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty sessionScope.user}">--%>
<%--                        <li><a class="nav-link active" aria-current="page" href="profile">Profile</a></li>--%>
<%--                        <li><a class="nav-link active" aria-current="page" href="logout">Logout</a></li>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <li><a class="nav-link active" aria-current="page" href="logins">Login</a></li>--%>
<%--                        <li><a class="nav-link active" aria-current="page" href="signup">Sign-up</a></li>--%>
<%--                    </c:otherwise>--%>
<%--                    </c:choose>--%>

<%--            </ul>--%>

        </div>
    </div>
</nav>
<br><br><br>


