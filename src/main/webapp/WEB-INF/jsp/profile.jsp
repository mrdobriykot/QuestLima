<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.entities.Role" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<c:import url="parts/header.jsp"/>

<h2>Профиль пользователя</h2>

<%--Admin has the right to edit all users--%>
<%--A user without admin rights can only edit his profile if his role != GUEST --%>
<c:choose>
    <c:when test="${(sessionScope.user.role == Role.ADMIN)
    || (sessionScope.user.id == requestScope.user.id)
    && sessionScope.user.role != Role.GUEST}">

        <c:if test="${not empty requestScope.errorMessage}">
            <div>${requestScope.errorMessage}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}${Page.EDIT_USER_SERVLET}" method="post" id="editProfileForm">
            <div id="editProfileLayer">

                <div class="modal">
                    <div class="modal-content">
                        <span class="close-button">&times;</span>
                        <div class="avatar-images">
                            <c:forEach var="image" items="${applicationScope.defaultAvatarsImages}">
                                <img src="${pageContext.request.contextPath}/img/default/avatars/${image}" alt="avatar"
                                     onclick="chooseDefaultAvatar(this)">
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div id="preview-avatar">
                    <img src="${pageContext.request.contextPath}/img/default/avatars/${requestScope.user.profileImage}"
                         alt="User profile image" title="User profile" class="profile-image"
                         onclick="chooseDefaultAvatar(this)">
                </div>
                <span class="trigger"></span>
                <div class="inputFields">
                    <label>Логин<sup>*</sup> :</label><br>
                    <input type="text" name="login" id="login" minlength="2" maxlength="30"
                           pattern="^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$"
                           placeholder="Login: 2-30 cyrillic latin digit" title="2-30 cyrillic latin digit" required
                           value="${requestScope.user.login}">
                </div>

                <div class="inputFields">
                    <label>Новый пароль:</label><br>
                    <input type="password" name="password" id="password" minlength="2" maxlength="30"
                           pattern="^[a-zA-ZА-Яа-яЁё0-9_-]{0,30}$"
                           placeholder="Password: 0-30 cyrillic latin digit" title="0-30 cyrillic latin digit">
                </div>
                <c:if test="${sessionScope.user.id != requestScope.user.id}">
                    <div class="inputFields">
                        <label>Роль: <br>
                            <select size="1" name="role">
                                <c:forEach var="role" items="${applicationScope.roles}">
                                    <option value="${role}" ${role==requestScope.user.role?"selected":""}>${role}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                </c:if>

                <div class="inputFields">
                    <label>Кол-во сыгранных игр (readonly):</label><br>
                    <input type="text" id="playedQuestsCount" minlength="1" maxlength="10"
                           title="Not editable" value="${requestScope.user.playedQuestsCount}" readonly>
                </div>
                <input type="hidden" name="image" id="avatar-image-id" value="${requestScope.user.profileImage}">
                <input type="hidden" name="id" value="${requestScope.user.id}">

                <input type="submit" value="Сохранить" class="submit">
            </div>
        </form>
    </c:when>
    <c:otherwise>

        <img src="${pageContext.request.contextPath}/img/default/avatars/${requestScope.user.profileImage}"
             alt="User profile image" title="User profile" class="profile-image">
        <br>
        Логин:  ${requestScope.user.login}
        <br>
        Роль:  ${requestScope.user.role}
        <br>
        Кол-во сыгранных игр: ${requestScope.user.playedQuestsCount}
    </c:otherwise>
</c:choose>

<c:import url="parts/footer.jsp"/>