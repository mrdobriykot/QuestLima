<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javarush.quest.sternard.util.Page" %>
<c:import url="parts/header.jsp"/>

<h2>Квест</h2>

<%--show form question and answers --%>
<c:if test="${not empty requestScope.questionAnswersNow}">
    <div id="show-quest">
        <div class="show-quest-image">
            <img src="${pageContext.request.contextPath}/img/quest/${requestScope.questionAnswersNow.image}"
                 alt="Quest image" title="Quest image">
        </div>
        <div id="show-quest-question-answer">
            <div id="show-quest-question">${requestScope.questionAnswersNow.text} </div>

            <form action="${pageContext.request.contextPath}${Page.QUEST_SERVLET}" method="post">
                <div id="show-quest-answers">
                    <c:forEach var="answer" items="${requestScope.questionAnswersNow.answers}">
                        <label class="flex">
                            <input type="radio" name="nextQuestionId" value="${answer.nextQuestionId}"> ${answer.answer}
                        </label>
                        <br>
                    </c:forEach>
                </div>

                <input type="hidden" name="id" value="${requestScope.questionAnswersNow.questId}">
                <input type="submit" value="Далее">
            </form>
        </div>
    </div>
</c:if>


<%--WIN or LOSE quest result--%>
<c:if test="${not empty requestScope.questWasEnded}">
    <div id="show-quest-result">
        <div id="quest-state"><h2> ${requestScope.questWasEnded.questState}</h2></div>
        <div class="show-quest-image">
            <img src="${pageContext.request.contextPath}/img/quest/${requestScope.questWasEnded.image}"
                 alt="Quest image" title="Quest image">
        </div>
        <div id="show-quest-result-text">
                ${requestScope.questWasEnded.text}
            <br>
            <hr>
            <br>
            <div id="quest-rating-container">
                <div id="rating-vote-result" style="display: none;">
                    Ваш голос принят!
                </div>
                <div id="quest-rating">
                    <img src="${pageContext.request.contextPath}/img/site/rating-down.png" alt="Rating Down"
                         onclick="questRating({ratingUpDown:['down'],id:['${requestScope.quest.id}']})" class="rating">
                    <span id="rating">${requestScope.quest.rating}</span>
                    <img src="${pageContext.request.contextPath}/img/site/rating-up.png" alt="Rating Up"
                         onclick="questRating({ratingUpDown:['up'],id:['${requestScope.quest.id}']})" class="rating">
                </div>

                <div id="quest-share">
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/site/share-fb.png" alt="Share with facebook" class="share">
                    </a>
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/site/share-twitter.png" alt="Share with twitter" class="share">
                    </a>
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/site/share-linkedin.png" alt="Share with linkedin" class="share">
                    </a>
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/site/share-vk.png" alt="Share with VK" class="share">
                    </a>
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/site/share-pinterest.png" alt="Share with VK" class="share">
                    </a>
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/site/share-twitter.png" alt="Share with VK" class="share">
                    </a>
                </div>
            </div>
        </div>

    <div id="quest-button-play-again">
        <form action="${pageContext.request.contextPath}${Page.QUEST_SERVLET}" method="post">
            <input type="hidden" name="id" value="${requestScope.questWasEnded.questId}">
            <input type="hidden" name="playQuestAgainId" value="${requestScope.questWasEnded.questId}">
            <input type="submit" value="Играть ещё раз" class="submit">
        </form>
    </div>
    </div>
</c:if>

<c:import url="parts/footer.jsp"/>