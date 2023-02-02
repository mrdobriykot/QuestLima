<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header-block.jsp"/>
<head>
    <title>The end</title>
</head>
<body>
<div class="w3-content" style="max-width:2000px;margin-top:46px">
    <div class="w3-container w3-content w3-left w3-padding-64" style="max-width:800px" id="band">
        <h1 class="w3-wide"><c:out value="${question.text}"></c:out></h1>
        <a class="w3-button w3-black w3-section" href="/">К списку квестов</a>
    </div>
</div>
<jsp:include page="statistic-block.jsp"/>
</body>
</html>