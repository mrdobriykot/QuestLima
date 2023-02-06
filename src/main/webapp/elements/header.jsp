<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <ul class="header-menu">
        <li class="header-menu-item">
            <a href="${pageContext.request.contextPath}/main" class="header-menu-item">Главное меню</a>
        </li>
        <c:if test="${sessionScope.user.currentGameId != 0}">
            <li class="header-menu-item">
                <a href="${pageContext.request.contextPath}/play" class="header-menu-item">Продолжить</a>
            </li>
        </c:if>
        <li class="header-menu-item">
            <a href="${pageContext.request.contextPath}/account" class="header-menu-item">Профиль (${sessionScope.user.login})</a>
        </li>
        <li class="header-menu-item">
            <a href="${pageContext.request.contextPath}/constructor" class="header-menu-item">Редактор квестов</a>
        </li>
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <li class="header-menu-item">
                <a href="${pageContext.request.contextPath}/users" class="header-menu-item">Пользователи</a>
            </li>
            <li class="header-menu-item">
                <a href="${pageContext.request.contextPath}/quests" class="header-menu-item">Управление квестами</a>
            </li>
        </c:if>
        <li class="header-menu-item">
            <a href="${pageContext.request.contextPath}/logout" class="header-menu-item">Выйти</a>
        </li>
    </ul>
</div>
