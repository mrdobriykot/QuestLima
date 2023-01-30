<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title>Стартовая</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<h1>Приключения в Черноземье</h1>
<br>
<div class="plaintext">Отправьтесь в путешествие по увлекательному миру Черноземья. Решайте головоломки, сражайтесь с монстрами, принимайте верные решения. </div>
<div>Пожалуйста, авторизуйтесь, чтобы продолжить.</div>
<br>
<div>
    <form action="/login">
        <button class="button button-blue" id="signIn">Войти</button>
    </form>
    <form action="/signup">
        <button class="button" id="signUp">Создать аккаунт</button>
    </form>
</div>
<br>
</body>
</html>