
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Игра</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous"
    >
</head>

<body>
<br>

<h4>${requestScope.question.questionText}</h4>

<form action="game?questionId=${requestScope.question.id}" method="post">
    <ul>
        <c:forEach var="answer" items="${requestScope.question.answers}">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="answerId" value="${answer.id}"
                       id="answer${answer.id}" formaction="game" formmethod="post">
                <label class="form-check-label" for="answer${answer.id}">
                        ${answer.answerText}
                </label>
            </div>
        </c:forEach>
    </ul>

    <div class=" form-group">
        <label class="col-md-4 control-label" for="submit"></label>
        <div class="col-md-4">
            <c:choose>
                <c:when test="${not empty requestScope.question.answers}">
                    <button id="submit" class="btn btn-success">Отправить</button>
                </c:when>
                <c:otherwise>
                    <button formaction="/" formmethod="get" id="submit" class="btn btn-warning">Новая игра</button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <br>
    <br>
    <br>
    Статистика:<br>
    IP address: ${sessionScope.ipAddress}<br>
    Имя в игре: ${sessionScope.playerName}<br>
    Количество игр: ${sessionScope.gameCounter}<br>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous">
</script>
</body>
</html>
