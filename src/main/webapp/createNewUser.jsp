<%--
  Created by IntelliJ IDEA.
  User: mrfre
  Date: 18.01.2023
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New User</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">
</head>
<body>

<form action="createNewUser" method="post">
<fieldset>

<legend>Menu</legend>

<!-- Text input-->
<div class="form-group">
    <label class="col-md-4 control-label" for="login">UserName</label>
    <div class="col-md-4">
        <input id="login" name="login" type="text" placeholder="login" class="form-control input-md">

    </div>
</div>

<!-- Password input-->
<div class="form-group">
    <label class="col-md-4 control-label" for="password">Password</label>
    <div class="col-md-4">
        <input id="password" name="password" type="password" placeholder="password" class="form-control input-md">

    </div>
</div>

<!-- Select Basic -->
<div class="form-group">
    <label class="col-md-4 control-label" for="role">Role</label>
    <div class="col-md-4">
        <select id="role" name="role" class="form-control">
            <c:forEach items="${applicationScope.roles}" var="role">
                <option>${role}</option>
                <option>superRole</option> <%-- testRole--%>
            </c:forEach>
        </select>

    </div>
</div>

<!-- Button (Double) -->
<div class="form-group">
    <label class="col-md-4 control-label" for="button1"></label>
    <div class="col-md-8">
        <button id="button1" name="create" class="btn btn-success">Create</button>
    </div>
</div>

</fieldset>
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
