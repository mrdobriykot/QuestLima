<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Результат</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<div><h1>${requestScope.questResult}</h1></div>
<div>${requestScope.message}</div>
</body>
</html>
