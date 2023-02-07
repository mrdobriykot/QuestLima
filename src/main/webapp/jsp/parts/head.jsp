<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <title>RPG</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body {
            background: url(${pageContext.request.contextPath}/images/bg.jpg) no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
