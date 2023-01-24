<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <title>Interview</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="center">
<h1>Interview</h1>

<c:forEach var="quest" items="${requestScope.quest}">
     ${quest.question}<br>
</c:forEach>
<br>
<input value="Не знаю ответ" type="submit" name="Не знаю ответ" class="btn_red"/>
<input value="Посмотреть ответ" type="submit" name="Посмотреть ответ" class="btn_gray"/>
<input value="Ответил" type="submit" name="Ответил" class="btn_green"/>


</body>
</html>
