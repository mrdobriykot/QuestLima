<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <ul class="header-menu">
        <li class="header-menu-item">
            <a href="/main" class="header-menu-item">Квесты</a>
        </li>
        <c:if test="${sessionScope.user.currentGameId != 0}">
        <li class="header-menu-item">
            <a href="/play" class="header-menu-item">Продолжить </a>
        </li>
        </c:if>
        <li class="header-menu-item">
            <a href="/account" class="header-menu-item">Профиль (${sessionScope.user.login})</a>
        </li>
        <li class="header-menu-item">
            <a href="/constructor" class="header-menu-item">Редактор квестов</a>
        </li>
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <li class="header-menu-item">
                <a href="/users" class="header-menu-item">Пользователи</a>
            </li>
        </c:if>
        <li class="header-menu-item">
            <a href="/logout" class="header-menu-item">Выйти</a>
        </li>
    </ul>
</div>
