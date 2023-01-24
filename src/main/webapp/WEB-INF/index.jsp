<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body>
<div class="center">
    <br>
    <h1>Делаем обучение интереснее!</h1>
    <br>
    <h4>Web-приложение поможет в обучении Java а так же подготовке к собеседованию.
        <p>Совмещаем практику и теорию )))</p>
    </h4>
    <br>
    <p class="color_red">Выберите один из вариантов</p>

    <a href="${pageContext.request.contextPath}/training" class="btn_gray">Подготовка к собеседованию</a>
    <a href="${pageContext.request.contextPath}/interview" class="btn_gray">Виртуальное собеседование</a>
</div>
</body>
</html>
