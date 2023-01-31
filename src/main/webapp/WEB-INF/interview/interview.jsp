<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Interview</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="background_interview">
<div class="center">
<h1 class="color_indigo">Виртуальное собеседование</h1>
<h4>Постарайтесь дать развернутый ответ.
    <p>Желательно проговаривайте его вслух.</p>
    <p>Очень хорошо когда в голове все понятно, но ВАЖНО правильно передать свою мысль.</p></h4>
<br>

<h2>Вопрос ${requestScope.index} из ${requestScope.size}
    <p>${requestScope.question.question}</p></h2>
<br>
<form action="interview" method="post">
    <input value="Не знаю ответ" type="submit" name="NO" class="btn_red"/>
    <input value="Узнать ответ" type="submit" name="ANSWER" class="btn_cornflowerblue"/>
    <input value="Ответил" type="submit" name="YES" class="btn_green"/>
</form>
<img src="images/view/ibfwibviwebvibweivieew2314.webp">
<br><br>
<a href="${pageContext.request.contextPath}/" class="btn_gray">Вернутся на главную</a>
</div>
</body>
</html>
