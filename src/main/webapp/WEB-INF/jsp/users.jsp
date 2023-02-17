<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.entities.Role" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>

<c:import url="parts/header.jsp"/>

<h2>Список всех пользователей</h2>
<c:if test="${not empty requestScope.users}">
    <DIV class="table">
        <DIV class="th center">№</DIV>
        <DIV class="th center">Аватар</DIV>
        <DIV class="th center">Пользователь</DIV>
        <DIV class="th center">Роль</DIV>

        <c:if test="${sessionScope.user.role==Role.ADMIN}">
            <DIV class="th center">
                <img src="${pageContext.request.contextPath}/img/site/edit-user-th.png" alt="Edit" title="Edit user">
            </DIV>
            <DIV class="th center">
                <img src="${pageContext.request.contextPath}/img/site/delete-user-th.png" alt="Edit" title="Edit user">
            </DIV>
        </c:if>

        <c:forEach var="user" items="${requestScope.users}" varStatus="number">
            <DIV class="row" id="${user.id}">
                <DIV class="cell center">${number.count}</DIV>
                <DIV class="cell center">
                    <img src="${pageContext.request.contextPath}/img/default/avatars/${user.profileImage}"
                         class="profile-image-in-table" alt="profile-image"></DIV>
                <DIV class="cell">
                    <a href="${pageContext.request.contextPath}${Page.PROFILE_SERVLET}?id=${user.id}"
                       class="${sessionScope.user.id!=user.id?"profile-link":"profile-link-user"}">${user.login}</a>
                </DIV>
                <DIV class="cell center">${user.role}</DIV>

                <c:if test="${sessionScope.user.role==Role.ADMIN}">
                    <DIV class="cell center">
                        <a href="${pageContext.request.contextPath}${Page.PROFILE_SERVLET}?id=${user.id}">
                            <img src="${pageContext.request.contextPath}/img/site/edit.png" alt="Edit"
                                 title="Edit user">
                        </a>
                    </DIV>
                    <DIV class="cell center">
                        <c:if test="${sessionScope.user != user}">
                            <label>
                                <img src="${pageContext.request.contextPath}/img/site/delete.png" alt="Delete"
                                     title="Delete user"
                                     onclick="deleteUser('${user.id}', '${user.login}')">
                            </label>
                        </c:if>
                    </DIV>
                </c:if>

            </DIV>
        </c:forEach>
    </div>
</c:if>

<c:if test="${empty requestScope.users}">
    На это странице нет пользователей
</c:if>

<c:if test="${requestScope.pagesCount != 0}">
    <div id="paginator">
        <c:forEach var="page" begin="1" end="${requestScope.pagesCount}">
            <c:choose>
                <c:when test="${requestScope.currentPage != page}">
                    <span class="pageNumber"><a href="${pageContext.request.contextPath}${Page.USERS_SERVLET}?pageNum=${page}">${page}</a></span>
                </c:when>
                <c:otherwise>
                    <span class="activePageNumber">${page}</span>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</c:if>

<c:import url="parts/footer.jsp"/>