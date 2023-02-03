<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Quest</title>
</head>
<body>
<%--${requestScope}--%>
<h1><%=request.getAttribute("quest_header")%>
</h1>
<br>
<br>
<%=request.getAttribute("question")%>

<br>
<%--<label>Выберите ответ:</label>--%>
<%--<div id="radios" >--%>
<%--    <input id="radio_1" name="variant" type="radio" value="1" checked/>--%>
<%--    <label for="radio_1">Отклонить вызов.</label><br/>--%>
<%--    <input id="radio_2" name="variant" type="radio" value="2"/>--%>
<%--    <label for="radio_2">Принять вызов.</label><br/>--%>
<%--    <input id="radio_3" name="variant" type="radio" value="3" />--%>
<%--    <label for="radio_3">Принять вызов.</label><br/>--%>
<%--    <input id="radio_4" name="variant" type="radio" value="4"/>--%>
<%--    <label for="radio_4">Принять вызов.</label><br/>--%>
<%--    <input id="radio_5" name="variant" type="radio" value="5"/>--%>
<%--    <label for="radio_5">Принять вызов.</label><br/>--%>

<%--</div>--%>

<%--<form action="${pageContext.request.contextPath}/DisplayAnswersServlet" method="post">--%>
<%--    <c:forEach var="answer" items="${answers}">--%>
<%--        <label>--%>
<%--            <input type="radio" name="variant"> ${answer.answerText}<br>--%>
<%--        </label>--%>
<%--    </c:forEach>--%>
<%--    <input type="submit" value="Ответить" onclick="makeChoice()">--%>
<%--    <br>--%>
<%--</form>--%>

<c:forEach var="answer" items="${answers}">

    <label>
        <input type="radio" name="variant" value="${answer.answer_number}"> ${answer.answerText}<br>
    </label>
</c:forEach>
<br>
<div>
    <button onclick="makeChoice()" type="submit">Ответить</button>
</div>

<script>
    function makeChoice() {
        <%--document.location.href = "${pageContext.request.contextPath}/display_quest.jsp"--%>
        // let variant = document.querySelector('input[name="variant"]:checked').value;
        let variant = document.querySelector('input[name="variant"]:checked').value;
        if (variant === "99") {
            document.location.href = "${pageContext.request.contextPath}/LoseServlet"
        } else if (variant === "100") {
            document.location.href = "${pageContext.request.contextPath}/WinServlet"
        } else {
            document.location.href = "${pageContext.request.contextPath}/DisplayAnswersServlet"
        }
    }

</script>

</body>
</html>
