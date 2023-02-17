<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.entities.Role" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<c:import url="parts/header.jsp"/>

<c:if test="${sessionScope.user.role == Role.ADMIN || sessionScope.user.role == Role.EDITOR}">
    <h2>Редактирование квеста</h2>

    <c:if test="${not empty requestScope.errorMessage}">
        <div>${requestScope.errorMessage}</div>
    </c:if>


    <form action="${pageContext.request.contextPath}${Page.EDIT_QUEST_SERVLET}" method="post" id="quest-edit-form" enctype="multipart/form-data">
        <div id="loginProfileLayer">

            <div class="inputFields">
                <label for="title">Название:</label><br>
                <input type="text" name="title" id="title" minlength="1" required value="${requestScope.quest.title}">
            </div>
            <div class="inputFields">
                <label for="description">Описание:</label><br>
                <textarea name="description" minlength="1" id="description" required cols="60"
                          rows="6">${requestScope.quest.description}</textarea>
            </div>
            <div class="inputFields">
                <label for="creationDate">Дата создания:</label><br>
                <input type="date" name="creationDate" id="creationDate" minlength="1" required
                       value="${requestScope.quest.creationDate}">
            </div>
            <div class="inputFields">
                <label for="views">Просмотры:</label><br>
                <input type="text" name="views" id="views" minlength="1" required value="${requestScope.quest.views}">
            </div>
            <div class="inputFields">
                <label for="rating-q">Рейтинг:</label><br>
                <input type="text" name="rating" id="rating-q" minlength="1" required
                       value="${requestScope.quest.rating}">
            </div>
            <div class="inputFields">
                <label>Автор: <br>
                    <select size="1" name="author">
                        <c:forEach var="author" items="${requestScope.users}">
                            <option value="${author.login}" ${author.login==requestScope.quest.author?"selected":""}>${author.login}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="inputFields">
                <label for="uploadImage">Изображение:</label><br>
                <div class="quests-image">
                <img src="${pageContext.request.contextPath}/img/quest/${requestScope.quest.image}" alt="Quest image" id="preview-image"  style="float:none">
                </div>
                <br>
                <input type="file" name="image" id="uploadImage"  onclick="previewUploadingImage(this)">
            </div>
            <input type="hidden" name="id" value="${requestScope.quest.id}">
            <input type="hidden" name="image" value="${requestScope.quest.image}" id="image">
            <input type="submit" value="Сохранить" class="submit">
        </div>
    </form>
</c:if>

<c:import url="parts/footer.jsp"/>