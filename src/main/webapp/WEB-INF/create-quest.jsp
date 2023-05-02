<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<form action="create-quest" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Create Quest</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="questionName">Введите название квеста</label>
            <div class="col-md-4">
                <input id="questionName" name="questionName" type="text" placeholder="Name" class="form-control input-md">
                <span class="help-block"></span>
            </div>
        </div>


        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="question">Введите вопрос 1</label>
            <div class="col-md-4">
                <input id="question" name="question" type="text" placeholder="question" class="form-control input-md">
                <span class="help-block"></span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="answer">Введите ответ 1</label>
            <div class="col-md-4">
                <input id="answer" name="answer" type="text" placeholder="Введите ответ 1" class="form-control input-md">
                <span class="help-block"></span>
            </div>
        </div>
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="anotherAnswer">Введите ответ 2</label>
            <div class="col-md-4">
                <input id="anotherAnswer" name="anotherAnswer" type="text" placeholder="Введите ответ 2" class="form-control input-md">
                <span class="help-block"></span>
            </div>
        </div>

        <!-- File Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="questionImage">Выберите картинку</label>
            <div class="col-md-4">
                <input id="questionImage" name="questionImage" class="input-file" type="file">
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="createButton"></label>
            <div class="col-md-4">
                <button id="createButton" name="createButton" class="btn btn-primary">Создать квест</button>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="nextButton"></label>
            <div class="col-md-4">
                <button id="nextButton" name="nextButton" class="btn btn-primary">Следующий вопрос</button>
            </div>
        </div>



    </fieldset>
</form>


<%--<div class="container">--%>
<%--    <form action="create-quest" method="post">--%>

<%--        <div class="mb-3">--%>
<%--            <label for="quest-name" class="form-label">Название квеста</label>--%>
<%--            <input name="name" type="text" class="form-control" id="quest-name" placeholder="Тут укажите имя квеста">--%>
<%--        </div>--%>

<%--        <div class="mb-3">--%>
<%--            <label for="exampleFormControlTextarea1" class="form-label">Содержимое квеста</label>--%>
<%--            <textarea name="text" class="form-control" id="exampleFormControlTextarea1" rows="10"--%>
<%--                      placeholder="<%@include file="./parts/quest-demo.jsp" %>"></textarea>--%>
<%--        </div>--%>

<%--        <div class=" form-group">--%>
<%--            <label class="col-md-4 control-label" for="submit"></label>--%>
<%--            <div class="col-md-4">--%>
<%--                <button id="submit" name="create"--%>
<%--                        class="btn btn-success">Создать квест</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
<c:import url="parts/footer.jsp"/>

