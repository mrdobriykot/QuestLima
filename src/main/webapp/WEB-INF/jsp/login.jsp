<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="parts/header.jsp"/>
<%@ page import="com.javarush.quest.sternard.util.Page" %>

<h2>Авторизоваться</h2>
<%-- show error message if exist--%>
<c:if test="${not empty requestScope.errorMessage}">
    <div>
            ${requestScope.errorMessage}
    </div>
</c:if>

        <form action="${pageContext.request.contextPath}${Page.LOGIN_SERVLET}" method="post" id="loginProfileForm">
            <div id="loginProfileLayer">

                <div class="inputFields">
                    <label>Логин :</label><br>
                    <input type="text" name="login" id="login" minlength="2" maxlength="30"
                           pattern="^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$"
                           placeholder="Login: 2-30 cyrillic latin digit" title="2-30 cyrillic latin digit" required value="Login1">
                </div>

                <div class="inputFields">
                    <label>Пароль:</label><br>
                    <input type="password" name="password" id="password" minlength="2" maxlength="30"
                           pattern="^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$"
                           placeholder="Password: 2-30 cyrillic latin digit" title="2-30 cyrillic latin digit" required  value="Login1">
                </div>
                <input type="submit" value="Авторизоваться" class="submit">
            </div>
        </form>

<c:import url="parts/footer.jsp"/>