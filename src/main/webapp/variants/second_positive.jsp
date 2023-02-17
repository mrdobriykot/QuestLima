<%--suppress CheckTagEmptyBody --%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>SecondPositive</title>
</head>
<body>
<h2>Ты поднялся на мостик. Ты кто?</h2>

<div id="radios">
    <input id="radio_1" name="variant" type="radio" value="1" checked/>
    <label for="radio_1">Солгать о себе.</label><br/>
    <input id="radio_2" name="variant" type="radio" value="2"/>
    <label for="radio_2">Рассказать правду о себе.</label><br/>
</div>

<br>

<div>
    <button onclick="makeChoice()" type="submit">Ответить</button>
</div>

<script>
    function makeChoice() {
        let variant = document.querySelector('input[name="variant"]:checked').value;
        if (variant === "1") {
            document.location.href = "${pageContext.request.contextPath}/ThirdNegativeServlet"
        } else if (variant === "2") {
            document.location.href = "${pageContext.request.contextPath}/ThirdPositiveServlet"
        }
    }

</script>

<jsp:include page="../statistic.jsp"></jsp:include>
</body>
</html>
