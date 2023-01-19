<%@include file="../pageConstructor/header.jsp"%>

<form action="loginServlet" method="post">
  <fieldset>

    <legend>Auth</legend>

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

    <div class="form-group">
      <label class="col-md-4 control-label" for="button1"></label>
      <div class="col-md-8">
        <button id="button1" name="create" class="btn btn-success">log in</button>
      </div>
    </div>

  </fieldset>
</form>

<%@include file="../pageConstructor/footer.jsp"%>

