<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Успех</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<div class="plaintext">${requestScope.message}</div>
</body>
</html>
