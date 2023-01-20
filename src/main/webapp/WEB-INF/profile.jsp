<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>

<jsp:useBean id="user" scope="session"
             type="com.javarush.quest.marzhiievskyi.entity.User"/>


<form class="form-horizontal" action="profile" method="post" enctype="multipart/form-data"
      style="width: 70%; margin: auto">
    <fieldset>

        <!-- Form Name -->
        <legend style="text-align: center">${user.login} profile</legend>
        <div class="bd-example">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Player login: ${user.login}</li>
                <li class="list-group-item">Player password: ${user.password}</li>
                <li class="list-group-item">Total games: there wil be shown total info about played games</li>
                <li class="list-group-item">Some info about player: </li>
            </ul>
        </div>
        <!-- Button -->
        <div class="form-group" >
            <div class="col-md-4" style=" margin: auto;text-align: center">
                <button id="user" name="user" class="btn btn-primary" style="width: 20%">Edit</button>
            </div>
        </div>

    </fieldset>
</form>


<%@include file="parts/footer.jsp" %>
