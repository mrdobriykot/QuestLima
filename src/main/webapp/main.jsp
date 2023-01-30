<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Главная</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<c:if test="${requestScope.isInGame == true}">
<div><h1>Текущая игра: </h1></div>
    <table class="table">
        <c:forEach var="quest" items="${requestScope.quests}">
            <tr>
                <td>${currentGameName}</td>
                <td><form action="/play" method="GET"><button class="button button-green">Продолжить</button></form></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<div><h1>Доступные квесты:</h1></div>
<div>
    <table class="table">
    <c:forEach var="quest" items="${requestScope.quests}">
        <tr>
            <td>${quest.name}</td>
            <td><form action="/newGame" method="POST">
                <button class="button button-blue" name="questId" value="${quest.id}">Начать</button>
            </form></td>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>
