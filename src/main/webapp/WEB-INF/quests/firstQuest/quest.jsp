<%@include file="../../pageConstructor/header.jsp"%>


<c:choose>
<c:when test="${not empty requestScope.lose}">
        ${requestScope.lose}
</c:when>
<c:otherwise>

        ${requestScope.question1.getText()}
        <br>

        <form action="quest" method="post">
                Answer: <input type="radio" name="answer" value="true"/>${requestScope.answers.get(0).getText()}
                <input type="radio" name="answer" value="false"/>${requestScope.answers.get(1).getText()}
                <br>
                <input type="submit" value="Continue"/>
        </form>
</c:otherwise>
</c:choose>



<%@include file="../../pageConstructor/footer.jsp"%>