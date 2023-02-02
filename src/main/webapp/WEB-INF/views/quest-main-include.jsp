<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<jsp:include page="header-block.jsp"/>
<head>
    <title>Quest main page</title>
</head>
<body>
<div class="w3-content" style="max-width:2000px;margin-top:46px">
    <div class="w3-container w3-content w3-left w3-padding-64" style="max-width:800px" id="band">
        <h1 class="w3-wide"><c:out value="${quest.name}"></c:out></h1>
        <p class="w3-opacity"><c:out value="${quest.description}"></c:out></p>
        <a class="w3-button w3-black w3-section" href="question?id=${quest.startQuestionId}">Let's start!</a>
    </div>
</div>
</body>
</html>
