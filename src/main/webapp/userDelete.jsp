<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Удалить пользователя</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
Вы уверены, что хотите удалить пользователя ${requestScope.userToBeModified.login}?
<form action="/userDelete" method="POST">
<button class="button" class="button-red" name="userId" value="${requestScope.userToBeModified.id}">Удалить пользователя</button>
</form>
<form action="/users" method="GET">
<button class="button" class="button-blue">Вернуться к списку пользователей </button>
</form>
</body>
</html>
