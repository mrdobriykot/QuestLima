<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/2/2023
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="parts/header.jsp"%>
<form class="form-horizontal">
    <fieldset>
        <div>
            <img src="images/${sessionScope.user.getImage()}" alt="images/${sessionScope.user.getImage()}" width="100px">
            <h1>Profile</h1>
        </div>
    </fieldset>
</form>
<%@include file="parts/footer.jsp"%>
