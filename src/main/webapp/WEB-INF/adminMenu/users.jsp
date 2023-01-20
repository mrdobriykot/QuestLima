<%@include file="../pageConstructor/header.jsp"%>
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

<form class="form-horizontal" action="createNewUser" method="get">
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

<%@include file="../pageConstructor/footer.jsp"%>

