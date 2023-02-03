<%@include file="parts/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>



<form class="form-horizontal" action="game?id=${requestScope.quest.id}" method="post"
style="text-align: center">
    <fieldset>

        <!-- Form Name -->
        <legend>${requestScope.quest.name}</legend>
        <br>
        <br>

        <!-- Text question -->
        <c:if test="${requestScope.questionId==1}">
            <h5>${requestScope.quest.startingText}</h5>
            <br>
            <br>
        </c:if>



        <h6>${requestScope.question}</h6>
        <br>

        <div class="form-group">

            
            <div class="col-md-8" style="text-align: center; width: 70%; margin: auto">
                <c:forEach var="answer" items="${requestScope.answers}">
                    <button style=" margin-top: 10px;  width: 300px" id="answer" name="answer" value="${answer.nextQuestionId}" class="btn btn-primary">${answer.text}</button>
                </c:forEach>
            </div>
        </div>

    </fieldset>
</form>


<%@include file="parts/footer.jsp" %>
