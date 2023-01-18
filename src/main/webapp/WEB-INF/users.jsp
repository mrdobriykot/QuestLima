<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>


<c:forEach var="user" items="${requestScope.users}">
    <a href="user?id=${user.id}"> ${user.login}</a>
    <br>
</c:forEach>
<br><br>
<a href="user?id=0">Create new user</a>


<%@include file="parts/footer.jsp" %>
