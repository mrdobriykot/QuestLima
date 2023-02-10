<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp"%>
<jsp:useBean id="quest" scope="request" class="com.bogdanov.entity.Quest"/>
<form class="form-horizontal" action="gameMenu" method="get" enctype="multipart/form-data">
    <fieldset>

        <h1>${requestScope.count==requestScope.size && requestScope.status?"Выйграл, молодец!":"Проиграл."}</h1>

        <div class="col-md-8">
            <button id="updateOrCreate" name="newChange" class="btn btn-success">Вернуться</button>
        </div>
    </fieldset>
</form>
<%@include file="parts/footer.jsp"%>
