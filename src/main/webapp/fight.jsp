<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
  <title>Бой</title>
  <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<div><h1>Бой: ${sessionScope.fight.villain.name}</h1></div>
<br>
<div class="plaintext">${requestScope.message}</div>
<br>
<div>
  <form action="/fight" method="POST">
    <fieldset>
      <div>Нанести удар: </div>
      <c:forEach var="hitOption" items="${sessionScope.fight.hitOptions}">
        <input class="margin" type="radio" id="hitOption${hitOption}" name="hitOption" value="${hitOption}">
        <label for="hitOption${hitOption}">${hitOption}</label><br>
      </c:forEach>
      <br>
      <div>Поставить блок: </div>
      <c:forEach var="blockOption" items="${sessionScope.fight.blockOptions}">
        <input class="margin" type="radio" id="blockOption${blockOption}" name="blockOption" value="${blockOption}">
        <label for="blockOption${blockOption}">${blockOption}</label><br>
      </c:forEach>
      <br>
      <button type="submit" class="button button-blue">Ударить</button>
    </fieldset>
  </form>
</div>
<div>
  <h2>Что случилось в бою: </h2>
  <c:forEach var="fightLog" items="${sessionScope.fight.fightLog}">
    ${fightLog}<br>
  </c:forEach>
</div>
</body>
</html>
