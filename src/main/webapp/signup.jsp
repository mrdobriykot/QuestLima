<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Логин</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<br>
<form class="form" action="${pageContext.request.contextPath}/signup" method="POST">
    <fieldset>
        <legend>Пожалуйста, создайте аккаунт</legend>
        <label for="text">Логин</label>
        <input class="margin" type="text" id="text" name="login" placeholder="login" required/><br>
        <label for="stacked-password">Password</label>
        <input class="margin" type="password" id="stacked-password" name="password" required/><br>
        <button type="submit" class="button button-blue">Создать аккаунт</button>
    </fieldset>
</form>
<br>
</body>
</html>
