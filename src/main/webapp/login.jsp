<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Логин</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<br>
<form class="form" action="/login" method="POST">
    <fieldset>
        <legend>Пожалуйста, авторизуйтесь</legend>
        <label for="text">Логин</label>
        <input type="text" id="text" name="login" placeholder="login" />
        <label for="stacked-password">Password</label>
        <input type="password" id="stacked-password" name="password" />
        <button type="submit" class="button button-blue">Авторизоваться</button>
    </fieldset>
</form>
<br>
</body>
</html>
