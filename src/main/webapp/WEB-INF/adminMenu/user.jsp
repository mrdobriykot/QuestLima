<%@include file="../pageConstructor/header.jsp"%>


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

<%@include file="../pageConstructor/footer.jsp"%>
