
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
   <title>FirstNegative</title>

</head>
<body>
<h2>Проигрыш! Хотите начать заново?</h2>
<form action="${pageContext.request.contextPath}/hello-servlet" method="get">
    <input type="submit" value="Начать Заново">
</form>
<form action="${pageContext.request.contextPath}/RestartServlet" method="get">
    <input type="submit" value="Выйти">
</form>

<%--suppress CheckTagEmptyBody --%>
<jsp:include page="../statistic.jsp"></jsp:include>
</body>
</html>
