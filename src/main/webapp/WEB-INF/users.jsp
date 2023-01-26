<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>

<div class="bd-example-snippet bd-code-snippet" style="width: 70%; margin: auto">
    <div class="bd-example">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Login</th>
                <th scope="col">Password</th>
                <th scope="col">Games</th>
                <th scope="col">Wins</th>
                <th scope="col">Losses</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${fn:length(user.games)}</td>
                    <td>${fn:length(user.wins)}</td>
                    <td>${fn:length(user.losses)}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<%@include file="parts/footer.jsp" %>
