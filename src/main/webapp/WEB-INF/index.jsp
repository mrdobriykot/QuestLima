<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="background_index">
<div class="center">
    <h1 class="color_indigo">Делаем обучение интереснее!</h1>
    <h4>Web-приложение поможет в обучении Java а так же подготовке к собеседованию.
        <p>Совмещаем практику и теорию )))</p>
    </h4>
    <h4 class="color_red">Выберите один из вариантов</h4>

    <a href="${pageContext.request.contextPath}/training" class="btn_gray">Подготовка к собеседованию</a>
    <a href="${pageContext.request.contextPath}/interview" class="btn_gray">Виртуальное собеседование</a>
    <br>
    <img src="images/view/13-1024x683.png">
</div>
</body>
</html>
