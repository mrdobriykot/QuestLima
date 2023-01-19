<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>
<form class="form-horizontal" action="user?id=${requestScope.user.id==null?0:requestScope.user.id}" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend style="text-align: center">Edit your account</legend>

        <!-- Text input-->
        <div class="form-group" style="text-align: center">
            <label class="col-md-4 control-label" for="userlogin">Login</label>
            <div class="col-md-4" style="margin: auto">
                <input id="userlogin" name="login" type="text" placeholder=""
                       class="form-control input-md" required=""
                       value="${requestScope.user.login}">

            </div>
        </div>
        <br>

        <!-- Password input-->
        <div class="form-group" style="text-align: center">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4" style="margin: auto">
                <input id="password" name="password" type="password" placeholder=""
                       class="form-control input-md" required=""
                       value="${requestScope.user.password}">
            </div>
        </div>
        <br>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-8" style="text-align: center; width: 70%; margin: auto">
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

<%@include file="parts/footer.jsp" %>
