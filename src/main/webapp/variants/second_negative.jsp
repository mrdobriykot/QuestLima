<%--suppress CheckTagEmptyBody --%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>SecondNegative</title>

</head>
<body>
<h2>Ты не пошел на переговоры. Поражение!</h2>
<form action="${pageContext.request.contextPath}/hello-servlet" method="get">
    <input type="submit" value="Начать Заново">
</form>
<form action="${pageContext.request.contextPath}/RestartServlet" method="get">
    <input type="submit" value="Выйти">
</form>

<jsp:include page="../statistic.jsp"></jsp:include>
</body>
</html>
