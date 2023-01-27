<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Пролог</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous"
    >
</head>
<body>
<h1>Пролог
</h1>
<br/>
<p>
    Ты стоишь в космическом порту и готов подняться на борт<br>
    своего корабля. Разве ты не об этом мечтал? Стать капитаном<br>
    галактического судна с экипажем, который будет совершать<br>
    подвиги под твоим командованием.<br>
    Так что вперед!
</p>
<br/>
<h1>Знакомство с экипажем</h1>
<p>
    Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках:<br>
    - Здравствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе<br>
    наш штурман - сержант Перегарный Шлейф, под штурвалом спит наш бортмеханик - Черный Богдан,<br>
    а фотографирует его Сергей Стальная Пятка - наш навигатор.<br>
    А как обращаться к вам?
</p>
<form class="form-horizontal" action="${pageContext.request.contextPath}/game" method="get">
    <fieldset>

        <!-- Form Name -->
        <legend></legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput"></label>
            <div class="col-md-4">
                <input id="textinput" name="name" type="text" placeholder="введите имя" class="form-control input-md">

            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for=""></label>
            <div class="col-md-4">
                <button id="" name="" class="btn btn-info">Представиться</button>
            </div>
        </div>

    </fieldset>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous">
</script>

</body>
</html>