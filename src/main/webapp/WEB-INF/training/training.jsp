<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.javarush.quest.osypenko.repository.Entity" %>
<html>
<head>
    <title>Quest</title>
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="background_training">
<div class="center">
    <br>
    <h1 class="color_indigo">Выберите тему для изучения</h1>
    <br>

    <form action="table" method="get">

        <c:forEach var="value" items="${Entity.values()}">
            <input value="${value}" type="submit" name="${value}" class="btn_gray"/>
        </c:forEach>

    </form>
    <img src="images/view/c4i0TWtk1qE.jpg">
</div>
</body>
</html>
