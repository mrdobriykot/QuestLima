<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="header-block.jsp"/>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<div class="w3-content" style="max-width:2000px;margin-top:46px">
    <div class="w3-container w3-content w3-left w3-padding-64" style="max-width:800px" id="band">
        <h1 class="w3-wide">🎯 Выберите квест</h1>
        <c:forEach items="${requestScope.questList}" var="quest">
            <a class="w3-button w3-black w3-section" href="quest?id=${quest.id}"><c:out value="${quest.name}"></c:out></a>
        </c:forEach>
    </div>
</div>
</body>
</html>