<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Вопрос</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<div><h1>Вопрос</h1></div>
<br>
<div class="plaintext">${requestScope.message}</div>
<br>
<div>
    <form action="/question" method="POST">
        <fieldset>
        <c:forEach var="option" items="${options}">
        <input class="margin" type="radio" id="option${option.id}" name="optionId" value="${option.id}">
        <label for="option${option.id}">${option.name}</label><br>
        </c:forEach>
        <button type="submit" class="button button-blue">Ответить</button>
        </fieldset>
    </form>
</div>
</body>
</html>
