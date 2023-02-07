<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Редактировать пользователя</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<br>
<div>
<form class="form" action="${pageContext.request.contextPath}/userModify" method="POST">
    <fieldset>
        <legend>Изменение пользователя ${requestScope.userToBeModified.login} </legend><br>
        <label for="userId">ID</label>
        <input class="margin" type="text" id="userId" name="userId" value="${requestScope.userToBeModified.id}" readonly/><br>
        <label for="login">Логин</label>
        <input class="margin" type="text" id="login" name="login" value="${requestScope.userToBeModified.login}" /><br>
        <label for="stacked-password">Password</label>
        <input class="margin" type="text" id="stacked-password" name="password" value="${requestScope.userToBeModified.password}"/><br>
        <label for="stacked-state">Роль</label>
        <select class="margin" id="stacked-state" name="role">
            <option selected>${requestScope.userToBeModified.role}</option>
            <c:forEach var="role" items="${requestScope.roles}">
                <c:if test="${role != requestScope.userToBeModified.role}">
                    <option>${role}</option>
                </c:if>
            </c:forEach>
        </select><br>
        <button type="submit" class="button button-blue">Изменить данные</button>
    </fieldset>
</form>
</div>
<br>
</body>
</html>
