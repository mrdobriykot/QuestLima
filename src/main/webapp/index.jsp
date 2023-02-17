<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Greeting</title>
</head>
<body>
<h2>Пролог
</h2>
<div class="first"> Ты стоишь в космическом порту и готов подняться на борт<br>
    своего корабля. Разве ты не об этом мечтал? Стать капитаном<br>
    галактического судна с экипажем, который будет совершать<br>
    подвиги под твоим командованием.<br>
    Так что вперед!<br></div>
<br>

<h2>Знакомство с экипажем</h2>
<div class="first"> Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках:<br>
   - Здравствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе <br>
    наш штурман - сержант Перегарный Шлейф, под штурвалом спит наш бортмеханик - Черный Богдан,<br>
    а фотографирует его Сергей Стальная Пятка - наш навигатор.<br>
    А как обращаться к вам?<br></div>
<br>

<form action="${pageContext.request.contextPath}/hello-servlet" method="post">
    <label>
        <input type="text" name="userName" >
    </label>
    <input type="submit" value="Представиться">
</form>


</body>
</html>