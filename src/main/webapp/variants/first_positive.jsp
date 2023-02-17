<%--suppress CheckTagEmptyBody --%>
<%--
  Created by IntelliJ IDEA.
  User: idob2
  Date: 05.01.2023
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>FirstPositive</title>
</head>
<body>
<h2>Ты принял вызов. Поднимешься на мостик к капитану?</h2>

<div id="radios">
    <input id="radio_1" name="variant" type="radio" value="1" checked/>
    <label for="radio_1">Отказаться подниматься на мостик.</label><br/>
    <input id="radio_2" name="variant" type="radio" value="2"/>
    <label for="radio_2">Подняться на мостик.</label><br/>
</div>

<br>

<div>
    <button onclick="makeChoice()" type="submit">Ответить</button>
</div>

<script>
    function makeChoice() {
        let variant = document.querySelector('input[name="variant"]:checked').value;
        if (variant === "1") {
            document.location.href = "${pageContext.request.contextPath}/SecondNegativeServlet"
        } else if (variant === "2") {
            document.location.href = "${pageContext.request.contextPath}/SecondPositiveServlet"
        }
    }

</script>

<jsp:include page="../statistic.jsp"></jsp:include>
</body>
</html>
