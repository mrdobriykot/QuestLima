<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Answer</title>
    <link href="../../css/main.css" rel="stylesheet">
</head>

<body class="center">
<h2 class="color_indigo">Ответ</h2>
<table class="container">
    <tr>
        <td>
            ${requestScope.answer.answer}
            <c:forEach var="urls" items="${requestScope.answer.url}">
                <a href="${urls.value}">${urls.key}</a>
            </c:forEach>
            <c:forEach var="img" items="${requestScope.answer.images}">
                <img src="${img}">
            </c:forEach>
        </td>
    </tr>
</table>

</body>
</html>
