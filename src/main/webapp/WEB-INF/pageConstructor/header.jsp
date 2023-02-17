<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Site</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">
</head>

<body>

<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>

            <c:choose>
            <c:when test="${not empty sessionScope.user}">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 text-secondary">Home</a></li>
                <li><a href="questList" class="nav-link px-2 text-white">Quests List</a></li>
                <li><a href="createQuests" class="nav-link px-2 text-white">Create Quest</a></li>
                <li><a href="support" class="nav-link px-2 text-secondary">Support</a></li>
                <li><a href="donate" class="nav-link px-2 text-secondary">Donate</a></li>

            </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                    <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="support" class="nav-link px-2 text-secondary">Support</a></li>
                    <li><a href="donate" class="nav-link px-2 text-secondary">Donate</a></li>


                </ul>
            </c:otherwise>
            </c:choose>

<%--            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">--%>
<%--                <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..."--%>
<%--                       aria-label="Search">--%>
<%--            </form>--%>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                <div class="text-end">
                    <button type="button" class="btn btn-outline-light me-2" value="log"><a href="profile">Profile</a> </button>
                    <button type="button" class="btn btn-warning" value="reg"><a href="logOut">Logout</a></button>
                </div>
                </c:when>
                <c:otherwise>
                    <div class="text-end">
                        <button type="button" class="btn btn-outline-light me-2"><a href="login">Login</a> </button>
                        <button type="button" class="btn btn-warning"><a href="signup">Sign-Up</a></button>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>