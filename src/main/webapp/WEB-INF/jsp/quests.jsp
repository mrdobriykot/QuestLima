<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.entities.Role" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<c:import url="parts/header.jsp"/>

<h2>Квесты</h2>

<DIV id="all-quests">

    <c:forEach var="quest" items="${requestScope.allQuests}">
        <div class="quests" id="${quest.id}">
            <div class="quests-title">
                <a href="${pageContext.request.contextPath}${Page.QUEST_SERVLET}?id=${quest.id}" title="${quest.title}">${quest.title}</a>
            </div>
            <div class="quests-image">
                <img src="${pageContext.request.contextPath}/img/quest/${quest.image}" alt="Quest ${quest.title}"
                     title="${quest.title}">
            </div>
            <p>${quest.description}</p>
            <span class="quests-date">${quest.creationDate}</span>
            <span class="quests-views">${quest.views}</span>
            <span class="quests-rating">${quest.rating}</span>
            <span class="quests-author">${quest.author}</span>

            <c:if test="${sessionScope.user.role == Role.ADMIN or sessionScope.user.role == Role.EDITOR}">
            <span class="quests-delete">
                <span id="quest-delete-button" onclick="deleteQuest('${quest.id}', '${quest.title}')">Удалить</span>
            </span>
                <span class="quests-edit">
                <a href="${pageContext.request.contextPath}${Page.EDIT_QUEST_SERVLET}?id=${quest.id}">Редактировать</a>
            </span>
            </c:if>
        </div>
    </c:forEach>

    <c:if test="${empty requestScope.allQuests}">
        На это странице нет квестов
    </c:if>

    <c:if test="${requestScope.pagesCount != 0}">
        <div id="paginator">
            <c:forEach var="page" begin="1" end="${requestScope.pagesCount}">
                <c:choose>
                    <c:when test="${requestScope.currentPage != page}">
                        <span class="pageNumber"><a href="${pageContext.request.contextPath}${Page.QUESTS_SERVLET}?pageNum=${page}">${page}</a></span>
                    </c:when>
                    <c:otherwise>
                        <span class="activePageNumber">${page}</span>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:if>

</DIV>

<c:import url="parts/footer.jsp"/>