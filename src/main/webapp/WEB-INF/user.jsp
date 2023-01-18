<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>User: ${requestScope.user.login!=null?requestScope.user.login:"none"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">

</head>
<body>
<form class="form-horizontal" action="user?id=${requestScope.user.id==null?0:requestScope.user.id}" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>User menu</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="userlogin">Login</label>
            <div class="col-md-4">
                <input id="userlogin" name="login" type="text" placeholder=""
                       class="form-control input-md" required=""
                       value="${requestScope.user.login}">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder=""
                       class="form-control input-md" required=""
                       value="${requestScope.user.password}">

            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-8">
                <button id="save" name="${requestScope.id>0?"update":"create"}"
                        class="btn btn-success">${requestScope.id>0?"Update":"Create"}
                </button>
                <c:if test="${requestScope.id>0}">
                    <button id="delete" name="delete"
                            class="btn btn-danger">Delete
                    </button>
                </c:if>

            </div>
        </div>

    </fieldset>
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous">
</script>
</body>
</html>
