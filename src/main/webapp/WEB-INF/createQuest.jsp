<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp"%>
<jsp:useBean id="quest" scope="request" class="com.bogdanov.entity.Quest"/>
<form class="form-horizontal" action="createQuest?id=${quest.getId()>0?quest.getId():0}" method="post" enctype="multipart/form-data">
    <fieldset>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Name quest</label>
            <div class="col-md-4">
                <input id="textinput" name="quest" type="text" placeholder="Name question..." class="text-name-quest input-md" value="${requestScope.quest.getName()!=null?requestScope.quest.getName():"New Quest"}">

            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="textarea" >Quest description</label>
            <div class="col-md-4">
                <label for="textarea2"></label>
                <textarea class="form-control" id="textarea2" name="questDescription">
<%--                    ${quest.getText()}--%>
                        descrtiption
                </textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="textarea">Quest</label>
            <div class="col-md-4">
        <textarea class="form-control" id="textarea1" name="questionText" >
<%--            ${quest.getQuestionText()}--%>
                1.Quest
2.Quest
3.Quest
    </textarea>
            </div>
        </div>
        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textarea">Answers</label>
            <div class="col-md-4">
    <textarea class="form-control" id="textarea" name="answersText">
<%--        ${quest.getAnswerText()}--%>
1.1.Answer.T
1.2.Answer
2.1.Answer.T
2.2.Answer1
3.1.Answer.T
3.2.Answer
    </textarea>
            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="updateOrCreate"></label>
            <div class="col-md-8">
                <button id="updateOrCreate" name="${requestScope.quest.getId()>0?"update":"create"}" class="btn btn-success">${requestScope.quest.getId()>0?"Update":"Create"}</button>
                <button id="delete" name="delete" class="btn btn-danger">Delete</button>
            </div>
        </div>
    </fieldset>
</form>
<%@include file="parts/footer.jsp"%>
