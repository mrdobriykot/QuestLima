<%@include file="../pageConstructor/header.jsp"%>

Quests list:
<br>
<c:forEach var="quest" items="${applicationScope.quests}">
    <p>Play in <a href="quest?id=${quest.getId()}"> ${quest.getInfo()} </a> </p>
</c:forEach>

<%@include file="../pageConstructor/footer.jsp"%>