<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Table</title>
    <link href="css/main.css" rel="stylesheet">
</head>

<body>
<div class="center">
    <h1 class="color">${requestScope.attribute}</h1>
</div>
В базе по данной теме существует <var class="color_red">${requestScope.dbSize}</var> вопроса(ов).
<hr>
<table class="container">
    <tr class="color_blue">
        <th>#</th>
        <th>Вопрос</th>
        <th>Ответ</th>
    </tr>
    <c:forEach var="quest" items="${requestScope.dbCollection}">
        <tr>
            <td>${quest.id - requestScope.constantID}</td>
            <td class="color_red">${quest.question}</td>
            <td>
                    ${quest.answer}
                <c:forEach var="urls" items="${quest.url}">
                    <a href="${urls.value}">${urls.key}</a>
                </c:forEach>
                <p>
                    <img src="${quest.images}">
            </td>
        </tr>
    </c:forEach>
</table>
<br>
</body>
</html>
