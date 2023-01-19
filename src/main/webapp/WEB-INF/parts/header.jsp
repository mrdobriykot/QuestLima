<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <title>Index</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
        crossorigin="anonymous">
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
  <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
  </a>

  <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
    <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-dark">Home</a></li>
    <li><a href="#" class="nav-link px-2 link-dark">Start quest</a></li>
    <li><a href="${pageContext.request.contextPath}/profile" class="nav-link px-2 link-dark">My profile</a></li>
    <li><a href="${pageContext.request.contextPath}/users" class="nav-link px-2 link-dark">Players</a></li>
  </ul>

  <div class="col-md-3 text-end">
    <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <button type="button" class="btn btn-outline-primary me-2" onclick="document.location='logout'">Logout</button>
      </c:when>
      <c:otherwise>
        <button type="button" class="btn btn-outline-primary me-2" onclick="document.location='login'">Login</button>
        <button type="button" class="btn btn-primary" onclick="document.location='signup'">Sign-up</button>
      </c:otherwise>
    </c:choose>


  </div>
</header>