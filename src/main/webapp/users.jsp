<%--
  Created by IntelliJ IDEA.
  User: mrfre
  Date: 18.01.2023
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AllUsers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">
</head>
<body>
<c:forEach var="user" items="${requestScope.users}">
    <p>Edit user <a href="user?id=${user.id}"> ${user.userName} </a> </p>
</c:forEach>

<form class="form-horizontal" action="createNewUser.jsp" method="get">
    <fieldset>
        <!-- Form Name -->
        <legend></legend>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="createbutton"></label>
            <div class="col-md-4">
                <button id="createbutton" name="createbutton" class="btn btn-primary">Create new User</button>
            </div>
        </div>
    </fieldset>
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
