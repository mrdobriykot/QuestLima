<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Answer</title>
    <link href="css/main.css" rel="stylesheet">
</head>

<body class="background_index">
<div class="center">
    <h2 class="color_indigo">Ответ</h2>
    <table class="container">
        <tr>
            <td>
                ${requestScope.answer.answer}
                <c:forEach var="urls" items="${requestScope.answer.url}">
                    <a href="${urls.value}">${urls.key}</a>
                </c:forEach>
                    <br>
                <c:forEach var="img" items="${requestScope.answer.images}">
                    <img src="${img}">
                </c:forEach>
            </td>
        </tr>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/interview" class="btn_gray">Назад</a>
</div>
</body>
</html>
