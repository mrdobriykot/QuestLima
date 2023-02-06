<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Создать пользователя</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<br>
<form class="form" action="${pageContext.request.contextPath}/userCreate" method="POST">
    <fieldset>
        <legend>Создание нового пользователя </legend>
        <label for="login">Логин</label>
        <input class="margin" type="text" id="login" name="login" /><br>
        <label for="stacked-password">Пароль</label>
        <input class="margin" type="text" id="stacked-password" name="password"/><br>
        <label for="stacked-state">Роль</label>
        <select class="margin" id="stacked-state" name="role">
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
