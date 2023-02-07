<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">

    <h1>
        Статистика
    </h1>
<%--suppress HtmlDeprecatedAttribute --%>
    <table border="2" cellpadding="2">
        <tr>
            <th>Пользователь</th>
            <th>Количество игр</th>
        </tr>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.gamesCount}</td>
            </tr>
        </c:forEach>

    </table>

</div>
<%@include file="parts/footer.jsp" %>