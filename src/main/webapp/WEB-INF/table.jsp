<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Table</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="center">
<h4 class="color">${requestScope.attribute}</h4>
<hr>
<table class="container">
    <tr class="color_blue">
        <th>Вопрос</th>
        <th>Ответ</th>
    </tr>
    <c:forEach var="quest" items="${requestScope.values}">
        <tr>
            <td class="color_red">${quest.question}</td>
            <td>${quest.answer}</td>
        </tr>
    </c:forEach>
</table>
<br>
</body>
</html>
