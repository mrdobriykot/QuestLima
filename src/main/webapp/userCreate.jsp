<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Создать пользователя</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<br>
<form class="form" action="/userCreate" method="POST">
    <fieldset>
        <legend>Создание нового пользователя </legend>
        <label for="login">Логин</label>
        <input type="text" id="login" name="login" /><br>
        <label for="stacked-password">Пароль</label>
        <input type="text" id="stacked-password" name="password"/><br>
        <label for="stacked-state">Роль</label>
        <select id="stacked-state" name="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option>${role}</option>
            </c:forEach>
        </select><br>
        <button type="submit" class="button button-blue">Создать пользователя</button>
    </fieldset>
</form>
<br>
</body>
</html>
