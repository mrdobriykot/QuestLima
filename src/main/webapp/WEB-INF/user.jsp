<%--
  Created by IntelliJ IDEA.
  User: mrfre
  Date: 18.01.2023
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">

</head>
<body>


<form class="form-horizontal" action="user?id=${requestScope.user.id}" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Menu</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="login">UserName</label>
            <div class="col-md-4">
                <input id="login" name="login" type="text" value="${requestScope.user.userName}" placeholder="login" class="form-control input-md">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" value="${requestScope.user.password}" placeholder="password" class="form-control input-md">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="role">Role</label>
            <div class="col-md-4">
                <select id="role" name="role" class="form-control">
                    <c:forEach items="${applicationScope.roles}" var="role">
                    <option value="${role}" ${requestScope.user.role==role?"selected":""}>${role}</option>
                    </c:forEach>
                </select>

            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="button1"></label>
            <div class="col-md-8">
                <button id="button1" name="update" class="btn btn-success">Update</button>
                <button id="button2" name="delete" class="btn btn-danger" >Delete</button>
            </div>
        </div>

    </fieldset>
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
