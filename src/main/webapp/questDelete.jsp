<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Удалить пользователя</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
Вы уверены, что хотите удалить квест ${requestScope.quest.name}?<br>
<form action="/questDelete" method="POST">
    <button class="button button-red"
            name="questId" value="${requestScope.quest.id}">Удалить квест</button>
</form>
<form action="/quests" method="GET">
    <button class="button button-blue">Вернуться к списку квестов</button>
</form>
</body>
</html>
