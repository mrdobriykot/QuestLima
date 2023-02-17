<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<%@ page import="com.javarush.quest.sternard.entities.Role" %>
<c:import url="parts/header.jsp"/>

<c:if test="${sessionScope.user.role == Role.ADMIN}">
    <h2>Админ-панель редактирования настроек сайта</h2>
    <sub>Сделано только в учебных целях, чтобы проще можно было переключаться между базами данных</sub>
    <div id="content">
        <form action="${pageContext.request.contextPath}${Page.EDIT_SETTINGS_SERVLET}" method="post">
            <div>
                Выбрать базу данных: <br><br>
                <label>Квесты:
                    <select size="1" name="switchQuestRepos">
                        <c:forEach var="questRepos" items="${requestScope.questsRepos}">
                            <option value="${questRepos}">${questRepos}</option>
                        </c:forEach>
                    </select>
                </label>
                <br>
                <br>
                <label>Юзеры:
                    <select size="1" name="switchUserRepos">
                        <c:forEach var="userRepos" items="${requestScope.usersRepos}">
                            <option value="${userRepos}">${userRepos}</option>
                        </c:forEach>
                    </select>
                </label>
                <br>
                <br>
                <input type="submit" value="Сохранить" class="submit">
            </div>
        </form>
    </div>
</c:if>

<c:import url="parts/footer.jsp"/>