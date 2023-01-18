<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>

<jsp:useBean id="user" scope="session"
             type="com.javarush.quest.marzhiievskyi.entity.User"/>

<div class="px-4 py-5 my-5 text-center">
    <p class="lead mb-4">

    User login: ${user.login}<br>
    Total games: ${user.games}
    <div class="col-lg-6 mx-auto">
        <form class="form-horizontal" action="profile" method="post" enctype="multipart/form-data">
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-left">
                <button type="submit" name="user" class="btn btn-primary btn-lg px-4 gap-3">Edit login & password</button>
            </div>
        </form>
    </div>
</div>

<%@include file="parts/footer.jsp" %>
