<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">
</head>

<body>

<h1>This is start page for game</h1>


<div class="container">
    <h1>${requestScope.questions.getId}</h1>
    <form action="" class="form-horizontal" method="post">
        <div>
            <p>${questionText}</p>
        </div>
        <ul>
            <br>

            <div class="w3-content" style="max-width:2000px;margin-top:46px">
                <div class="w3-container w3-content w3-left w3-padding-64" style="max-width:800px" id="band">
                    <h1 class="w3-wide"><c:out value="${question.text}"></c:out></h1>
                    <form name="answers" action="question?id=${question.id}" method="post">
                        <c:forEach items="${requestScope.answerList}" var="answer">
                            <div class="w3-container w3-white">
                                <input type="radio" id="answer_${answer.id}" name="userAnswer" value="${answer.id}">
                                <label for="answer_${answer.id}">${answer.text}</label><br>
                            </div>
                        </c:forEach>
                        <input class="w3-button w3-black w3-section" type="submit" value="Ответить">
                    </form>
                </div>
            </div>
        </ul>

    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous">
</script>
</body>
</html>
