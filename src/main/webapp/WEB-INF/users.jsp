<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>

<!--
<a href="user?id=${user.id}"> ${user.login}</a>
-->
<c:forEach var="user" items="${requestScope.users}">
    ${user.login}
    <br>
</c:forEach>
<br><br>

<%@include file="parts/footer.jsp" %>
