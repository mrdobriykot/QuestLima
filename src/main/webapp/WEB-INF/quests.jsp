<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>

<%--<a href="user?id=${user.id}"> ${user.login}</a>--%>

<div class="bd-example-snippet bd-code-snippet" style="width: 70%; margin: auto">
    <div class="bd-example">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name of the game</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="quest" items="${requestScope.quests}">
                <tr>
                    <th scope="row">${quest.id}</th>
                    <td> <a href="quest?id=${quest.id}">${quest.name}</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<%@include file="parts/footer.jsp" %>
