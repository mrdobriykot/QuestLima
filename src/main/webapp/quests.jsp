<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Квесты</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<div><h1>Квесты</h1></div>
<div>
    <table class="table">
        <thead>
        <tr>
            <th>ID квеста</th>
            <th>Логин автора</th>
            <th>Название квеста</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <c:forEach var="quest" items="${requestScope.quests}">
            <tr>
                <td>${quest.questId}</td>
                <td>${quest.authorName}</td>
                <td>${quest.name}</td>
                <td><a class="button button-red" href="/questDelete?questId=${quest.questId}">Удалить</a></td>
            </tr>
            <br>
        </c:forEach>
    </table>
</div>
</body>
</html>
