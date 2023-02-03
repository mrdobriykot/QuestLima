
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp"%>
<jsp:useBean id="quest" scope="request" class="com.bogdanov.entity.Quest"/>
<form class="form-horizontal" action="gameQuests?id=${requestScope.numQuestion.getQuestId()}&numQuestion=${requestScope.numQuestion.getIdQuestion()}" method="post" enctype="multipart/form-data">
    <fieldset>
        <div class="form-group">
            <div class="col-md-4">
                <h1>${requestScope.numQuestion.getText()}</h1><br>
                <c:forEach items="${requestScope.numQuestion.getAnswers()}" var="answer">
                <div class="form-group">
                    <div class="col-md-4">
                        <div class="radio">
                            <label for="radios-0">
                                <input type="radio" name="radios" id="radios-0" value="${answer.getStatus()}">
                                    ${answer.getIdAnswer()}"."${answer.getText()}
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
            <div class="form-group">
                <div class="col-md-4">
                    <button id="singlebutton" name="nextQuestion" class="btn btn-primary">Next Question</button>
                </div>
            </div>
        </div>
    </fieldset>
</form>
<%@include file="parts/footer.jsp"%>
