<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/15/2023
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="parts/header.jsp"%>


<div>
    <c:forEach var="quest1" items="${requestScope.quest}">
        <a href="<c:url value="/gameQuests?id=${quest1.getId()}&numQuestion=0"/>">${quest1.getName()}</a><br><%--на какой сервлет направляется и какие данные с собой забирает в request--%>
    </c:forEach>
</div>

 <%@include file="parts/footer.jsp"%>
