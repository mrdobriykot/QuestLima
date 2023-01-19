<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>

<!--
<a href="user?id=${user.id}"> ${user.login}</a>
-->

<div class="bd-example-snippet bd-code-snippet" style="width: 70%; margin: auto">
    <div class="bd-example">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Login</th>
                <th scope="col">Password</th>
                <th scope="col">Games</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.games}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<%@include file="parts/footer.jsp" %>
