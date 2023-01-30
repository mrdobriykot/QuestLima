<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form class="form" action="/userModify" method="POST">
    <fieldset>
        <legend>Изменение пользователя ${requestScope.userToBeModified.login} </legend>
        <label for="userId">ID</label>
        <input type="text" id="userId" name="userId" value="${requestScope.userToBeModified.id}" readonly/>
        <label for="login">Логин</label>
        <input type="text" id="login" name="login" value="${requestScope.userToBeModified.login}" />
        <label for="stacked-password">Password</label>
        <input type="text" id="stacked-password" name="password" value="${requestScope.userToBeModified.password}"/>
        <label for="stacked-state">Роль</label>
        <select id="stacked-state" name="role">
            <option selected>${requestScope.userToBeModified.role}</option>
            <c:forEach var="role" items="${requestScope.roles}">
                <c:if test="${role != requestScope.userToBeModified.role}">
                    <option>${role}</option>
                </c:if>
            </c:forEach>
        </select>
        <button type="submit" class="button button-blue">Изменить данные</button>
    </fieldset>
</form>
</div>
<br>
</body>
</html>
