<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Удалить пользователя</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
Вы уверены, что хотите удалить пользователя ${requestScope.userToBeModified.login}?<br>
<form action="${pageContext.request.contextPath}/userDelete" method="POST">
    <button class="button button-red" name="userId" value="${requestScope.userToBeModified.id}">Удалить
        пользователя
    </button>
</form>
<form action="${pageContext.request.contextPath}/users" method="GET">
    <button class="button button-blue">Вернуться к списку пользователей</button>
</form>
</body>
</html>
