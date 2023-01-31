<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Result</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="background_result">
<div class="center">
    <h1 class="color_indigo">Результат собеседования</h1>

    <h3 class="color_red">Вы ответили на ${requestScope.result} вопросов из ${requestScope.interviewSize}. Это составляет ${requestScope.percentage}%</h3>

    <img src="images/view/1639314608_22-papik-pro-p-programmist-klipart-23.png">
    <br>
    <a href="${pageContext.request.contextPath}/" class="btn_gray">Вернутся на главную</a>
</div>
</body>
</html>
