<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<div><h1>Пользователи</h1></div>
<br>
<div>
    <form action="${pageContext.request.contextPath}/userCreate" method="GET">
        <button type="submit" class="button button-green">Создать пользователя</button>
    </form>
</div>
<div>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Роль</th>
                <th>Текущая игра</th>
                <th>Игр пройдено</th>
                <th>Игр выиграно</th>
                <th>Изменить</th>
                <th>Удалить</th>
            </tr>
        </thead>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.currentGameName}</td>
                <td>${user.gamesPlayed}</td>
                <td>${user.gamesWon}</td>
                <td><a class="button button-yellow" href="${pageContext.request.contextPath}/userModify?userId=${user.id}">Изменить</a></td>
                <td><a class="button button-red" href="${pageContext.request.contextPath}/userDelete?userId=${user.id}">Удалить</a></td>
            </tr>
            <br>
        </c:forEach>
    </table>
</div>
</body>
</html>
