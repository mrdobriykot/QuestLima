<%@include file="pageConstructor/header.jsp"%>

<form class="form-horizontal" action="userChangeProfile" method="post">
  <fieldset>

    <!-- Form Name -->
    <legend>Input your new name</legend>

    <!-- Text input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="login">UserName</label>
      <div class="col-md-4">
        <input id="login" name="login" type="text" value="${sessionScope.user.userName}" placeholder="login" class="form-control input-md">
      </div>
    </div>


    <!-- Button (Double) -->
    <div class="form-group">
      <label class="col-md-4 control-label" for="button1"></label>
      <div class="col-md-8">
        <button id="button1" name="change" class="btn btn-success">Change</button>
      </div>
    </div>

  </fieldset>
</form>

<form class="form-horizontal" action="userChangeProfile" method="post">
  <fieldset>

    <!-- Form Name -->
    <legend>Input your new password</legend>

    <!-- Password input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="password">Password</label>
      <div class="col-md-4">
        <input id="password" name="password" type="password" placeholder="password" class="form-control input-md">

      </div>
    </div>


    <!-- Button (Double) -->
    <div class="form-group">
      <label class="col-md-4 control-label" for="button1"></label>
      <div class="col-md-8">
        <button id="button2" name="change" class="btn btn-success">Change</button>
      </div>
    </div>

  </fieldset>
</form>

<%@include file="pageConstructor/footer.jsp"%>