<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
            <tr>
                <td>${requestScope.currentGameName}</td>
                <td><form action="${pageContext.request.contextPath}/play" method="GET"><button class="button button-green">Продолжить</button></form></td>
            </tr>
    </table>
</c:if>
<div><h1>Доступные квесты:</h1></div>
<div>
    <table class="table">
    <c:forEach var="quest" items="${requestScope.quests}">
        <tr>
            <td>${quest.name}</td>
            <td><form action="${pageContext.request.contextPath}/newGame" method="POST">
                <button class="button button-blue" name="questId" value="${quest.id}">Начать</button>
            </form></td>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>
