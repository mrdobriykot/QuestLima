<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<c:import url="parts/header.jsp"/>

<h2>Статистика</h2>
<div id="content">
    <DIV class="row">
        <DIV class="cell">Всего игроков</DIV>
        <DIV class="cell"><a href="${pageContext.request.contextPath}${Page.USERS_SERVLET}">${requestScope.playersCountAll}</a></DIV>
    </DIV>

    <DIV class="row">
        <DIV class="cell">Всего квестов</DIV>
        <DIV class="cell"><a href="${pageContext.request.contextPath}${Page.QUESTS_SERVLET}">${requestScope.questsCountAll}</a></DIV>
    </DIV>

    <DIV class="row">
        <DIV class="cell">Самые играющие пользователи:</DIV>
        <DIV class="cell">
            <c:forEach var="user" items="${requestScope.mostPlayingUsers}">
                <DIV class="row">
                    <DIV class="cell">
                        <a href="${pageContext.request.contextPath}/profile?id=${user.id}" class="profile-link">${user.login}</a>
                        (${user.playedQuestsCount})
                    </DIV>
                </DIV>
            </c:forEach></DIV>
    </DIV>

    <DIV class="row">
        <DIV class="cell">Лучшие квесты по рейтингу:</DIV>
        <DIV class="cell">
            <c:forEach var="quest" items="${requestScope.bestQuestsByRating}">
                <DIV class="row">
                    <DIV class="cell">
                        <a href="${pageContext.request.contextPath}/quest?id=${quest.id}" class="quest-link">${quest.title}</a>
                        (${quest.rating})
                    </DIV>
                </DIV>
            </c:forEach></DIV>
    </DIV>

    <DIV class="row">
        <DIV class="cell">Лучшие квесты по просмотрам:</DIV>
        <DIV class="cell">
            <c:forEach var="quest" items="${requestScope.bestQuestsByViews}">
                <DIV class="row">
                    <DIV class="cell">
                        <a href="${pageContext.request.contextPath}/quest?id=${quest.id}" class="quest-link">${quest.title}</a>
                        (${quest.views})
                    </DIV>
                </DIV>
            </c:forEach></DIV>
    </DIV>
</div>

<c:import url="parts/footer.jsp"/>