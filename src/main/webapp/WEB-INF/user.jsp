<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp"%>
<jsp:useBean id="user" scope="request" class="com.example.entity.User"/>
<form class="form-horizontal" action="user?id=${user.id==null?0:user.id}" method="post" enctype="multipart/form-data">
    <fieldset>

        <!-- Form Name -->
        <legend>User form</legend>

        <!-- File Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="image">Avatar</label>
            <div class="col-md-4">
                <input id="image" name="images" class="input-file" type="file">
            </div>
        </div>

        <input type="hidden" name="id" value="${requestScope.id}">

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Text Input</label>
            <div class="col-md-4">
                <input id="textinput" name="login" type="text" placeholder="" class="form-control input-md" value="${user.getLogin()}">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="passwordinput">Password Input</label>
            <div class="col-md-4">
                <input id="passwordinput" name="password" type="password" placeholder="" class="form-control input-md" value="${user.getPassword()}">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="role">Select Positon</label>
            <div class="col-md-4">
                <select id="role" name="role" class="form-control">


                    <c:forEach items="${applicationScope.roles}" var="role">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>


            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
          <label class="col-md-4 control-label" for="updateOrCreate"></label>
            <div class="col-md-8">
                <button id="updateOrCreate" name="${requestScope.user.getId()>0?"update":"create"}" class="btn btn-success">${requestScope.user.getId()>0?"Update":"Create"}</button>
                <button id="delete" name="delete" class="btn btn-danger">Delete</button>
            </div>
        </div>
    </fieldset>
</form>
<%@include file="parts/footer.jsp"%>
