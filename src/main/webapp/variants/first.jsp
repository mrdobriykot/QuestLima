<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>First</title>
</head>
<body>
<h2>Ты потерял память. Принять вызов НЛО?</h2>

<div id="radios">
    <input id="radio_1" name="variant" type="radio" value="1" checked/>
    <label for="radio_1">Отклонить вызов.</label><br/>
    <input id="radio_2" name="variant" type="radio" value="2"/>
    <label for="radio_2">Принять вызов.</label><br/>
</div>

<br>

<div>
    <button onclick="makeChoice()" type="submit">Ответить</button>
</div>

<%--suppress CheckTagEmptyBody --%>
<jsp:include page="../statistic.jsp"></jsp:include>


<script>
    function makeChoice() {
        let variant = document.querySelector('input[name="variant"]:checked').value;
        if (variant === "1") {
            document.location.href = "${pageContext.request.contextPath}/FirstNegativeServlet"
        } else if (variant === "2") {
            document.location.href = "${pageContext.request.contextPath}/FirstPositiveServlet"
        }
    }

</script>
</body>
</html>
