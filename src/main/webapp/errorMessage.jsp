<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<div><h1>Ошибка: </h1></div>
<div class="plaintext">${requestScope.message}</div><br>
<c:if test="${sessionScope.user != null}">
<form action="/main" method="GET">
    <div>
        <button class="button">Вернуться к Квестам</button>
    </div>
</form>
</c:if>
<form action="/" method="GET">
    <div>
        <button class="button button-blue">В начало</button>
    </div>
</form>
</body>
</html>
