<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header-block.jsp"/>
<head>
    <title>What's next?</title>
</head>
<body>
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
<jsp:include page="statistic-block.jsp"/>
</body>
</html>
