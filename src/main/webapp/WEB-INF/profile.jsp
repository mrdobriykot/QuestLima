<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/2/2023
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="parts/header.jsp"%>
<form class="form-horizontal" action="profile" method="post" enctype="multipart/form-data">
    <fieldset>
        <div class="block-person">
            <img class="avatar" src="images/${sessionScope.user.getImage()}" alt="images/${sessionScope.user.getImage()}" width="500px">
            <h1>"${sessionScope.user.getLogin()}"</h1>
                <label class="col-md-4 control-label" for="updateOrCreate"></label>
                <div class="col-md-8">
                    <button id="updateOrCreate" name="update" class="btn btn-success">"Update"</button>
                </div>

        </div>
    </fieldset>
</form>
<%@include file="parts/footer.jsp"%>
