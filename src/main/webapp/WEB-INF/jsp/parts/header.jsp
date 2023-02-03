<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.entities.Role" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<html>
<head>
    <title>${requestScope.pageTitle}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
<div id="container">
    <div class="navbar">
        <div class="logo"><a href="#">JavaRush</a></div>

        <ul class="main-nav">
            <li><a href="${pageContext.request.contextPath}/home">Домой</a></li>

            <c:if test="${sessionScope.user.role == Role.ADMIN}">
            <li>
                <a href="${pageContext.request.contextPath}/edit-settings">Админка</a>
            </li>
            </c:if>

            <li><a href="${pageContext.request.contextPath}${Page.QUESTS_SERVLET}">Квесты</a></li>
            <li> <a href="${pageContext.request.contextPath}${Page.USERS_SERVLET}">Игроки</a></li>
            <li> <a href="${pageContext.request.contextPath}${Page.STATISTIC_SERVLET}">Статистика</a></li>

            <c:choose>
                <c:when test="${empty sessionScope.user}">
            <li><a href="${pageContext.request.contextPath}${Page.LOGIN_SERVLET}">Войти</a></li>
                    <li><a href="${pageContext.request.contextPath}${Page.REGISTRATION_SERVLET}">Регистрация</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}${Page.LOGOUT_SERVLET}">Выйти</a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>