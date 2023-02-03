<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/16/2023
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp"%>
<jsp:useBean id="user" scope="request" class="com.example.entity.User"/>
<form class="form-horizontal" action="logins?id=${user.id=0}" method="post" enctype="multipart/form-data">
        <fieldset>

                <!-- Form Name -->
                <legend>User form</legend>



                <!-- Text input-->
                <div class="form-group">
                        <label class="col-md-4 control-label" for="textinput">Text Input</label>
                        <div class="col-md-4">
                                <input id="textinput" name="login" type="text" placeholder="" class="form-control input-md" value="">

                        </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                        <label class="col-md-4 control-label" for="passwordinput">Password Input</label>
                        <div class="col-md-4">
                                <input id="passwordinput" name="password" type="password" placeholder="" class="form-control input-md" value="">

                        </div>
                </div>

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
                                <button id="updateOrCreate" name="create" class="btn btn-success">"Create"</button>
                        </div>
                </div>

        </fieldset>
</form>
<%@include file="parts/footer.jsp"%>