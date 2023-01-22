<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.javarush.quest.osypenko.repository.Entity" %>
<html>
<head>
    <title>Quest</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body>
<div class="center">
    <br>
    <h1>Выберите тему для изучения</h1>
    <br>

    <form action="table" method="get">

        <c:forEach var="value" items="${Entity.values()}">
            <input value="${value}" type="submit" name="${value}" class="btn"/>
        </c:forEach>

    </form>
</div>
</body>
</html>
