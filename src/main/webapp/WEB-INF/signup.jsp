<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="parts/header.jsp" %>

<form class="form-horizontal" action="signup" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend style="text-align: center">Register form</legend>

        <!-- Text input-->
        <div class="form-group" style="text-align: center">
            <label class="col-md-4 control-label" for="userlogin">Login</label>
            <div class="col-md-4" style="margin: auto">
                <input id="userlogin" name="login" type="text" placeholder=""
                       class="form-control input-md" required="">
            </div>
        </div>
        <br>

        <!-- Password input-->
        <div class="form-group" style="text-align: center">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4" style="margin: auto">
                <input id="password" name="password" type="password" placeholder=""
                       class="form-control input-md" required="">
            </div>
        </div>
        <br>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-8" style="text-align: center; width: 70%; margin: auto">
                <button id="save" name=create"
                        class="btn btn-success">Sign-up
                </button>
            </div>
        </div>

    </fieldset>
</form>

<%@include file="parts/footer.jsp" %>
