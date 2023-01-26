<%@include file="parts/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<form class="form-horizontal" action="game?id=${requestScope.quest.id}" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>${requestScope.quest.name}</legend>

        <!-- Text question -->
        <c:if test="${requestScope.questionId==1}">
            <h3>${requestScope.quest.startingText}</h3>
        </c:if>



        <p>${requestScope.question}</p>
        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="answer${answer.nextQuestionId}">Select your way</label>
            <div class="col-md-8">
                <c:forEach var="answer" items="${requestScope.answers}">
                    <button style="width: 100px" id="answer${answer.nextQuestionId}" name="answer" value="${answer.nextQuestionId}" class="btn btn-primary">${answer.text}</button>
                </c:forEach>
            </div>
        </div>

    </fieldset>
</form>


<%@include file="parts/footer.jsp" %>
