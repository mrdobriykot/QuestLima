<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/15/2023
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="parts/header.jsp"%>


<div>
    <a href="signup?id=0">New</a><br>
    <c:forEach var="user" items="${requestScope.users}">
        <img src="images/${user.getImage()}" alt="images/${user.getImage()}" width="100px">
        Editor <a href="<c:url value="/user?id=${user.getId()}"/>">${user.getLogin()}</a><br><%--на какой сервлет направляется и какие данные с собой забирает в request--%>
    </c:forEach>
</div>

 <%@include file="parts/footer.jsp"%>
