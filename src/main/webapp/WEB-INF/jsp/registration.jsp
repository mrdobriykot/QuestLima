<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<c:import url="parts/header.jsp"/>

<h1>Регистрация нового пользователя</h1>

<%-- show error message if exist--%>
<c:if test="${not empty requestScope.errorMessage}">
    <div>
            ${requestScope.errorMessage}
    </div>
</c:if>

<form action="${pageContext.request.contextPath}${Page.REGISTRATION_SERVLET}" method="post" id="registrationProfileForm">
    <div id="registrationProfileLayer">
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
            <img src="${pageContext.request.contextPath}/img/default/avatars/avatar_no_image.jpg"
                 alt="User profile image" title="User profile" class="profile-image" onclick="chooseDefaultAvatar(this)">
        </div>
        <span class="trigger"></span>
        <div class="inputFields">
            <label>Логин :</label><br>
            <input type="text" name="login" id="login" minlength="2" maxlength="30"
                   pattern="^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$"
                   placeholder="Login: 2-12 cyrillic latin digit" title="2-30 cyrillic latin digit" required value="Login4">
        </div>

        <div class="inputFields">
            <label>Пароль:</label><br>
            <input type="password" name="passwordReg" id="password" minlength="2" maxlength="30"
                   pattern="^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$"
                   placeholder="Password: 2-30 cyrillic latin digit" title="2-30 cyrillic latin digit" required value="Login4">
        </div>
        <div class="inputFields">
            <label>Роль:<br>
                <select size="1" name="role">
                    <c:forEach var="role" items="${applicationScope.roles}">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <input type="hidden" name="image" id="avatar-image-id" value="avatar_no_image.jpg">
        <input type="submit" value="Зарегистрироваться" class="submit">

    </div>
</form>

<c:import url="parts/footer.jsp"/>