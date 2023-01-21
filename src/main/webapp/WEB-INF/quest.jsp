<%@include file="parts/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<form class="form-horizontal" action="quest?id=${requestScope.quest.id}" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>${requestScope.quest.name}</legend>

        <!-- Text question -->
        <h3>${requestScope.quest.startingText}</h3>
        <p></p>
        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="agree">Select your way</label>
            <div class="col-md-8">
                <button style="width: 100px" id="agree" name="agree" class="btn btn-primary">Agree</button>
                <button style="width: 100px" id="disagree" name="disagree" class="btn btn-primary">Disagree</button>
            </div>
        </div>

    </fieldset>
</form>


<%@include file="parts/footer.jsp" %>
