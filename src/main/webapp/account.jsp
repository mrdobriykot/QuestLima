<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<div><h1>Профиль</h1></div>
<br>
<div>
    Логин: ${sessionScope.user.login} <br>
    Роль: ${sessionScope.user.role} <br>
    Игр пройдено: ${sessionScope.numberOfGamesPlayed} <br>
    Игр выиграно: ${sessionScope.gamesWon} <br>
    <td><a class="button-warning" href="${pageContext.request.contextPath}/userModify?userId=${sessionScope.user.id}">Редактировать</a></td>
</div>
</body>
</html>
