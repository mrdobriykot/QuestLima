
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create_Quest</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/CreateQuestServlet" method="post">


<%--    <input id="quest_header" name="quest_text">Введите текст квеста</input>--%>
    <label for="quest_header" class="form-label">Описание квеста</label>
    <textarea name="quest_header" id="quest_header" rows="30" cols="30"></textarea>
    <label for="quest_text" class="form-label">Содержимое квеста</label>
    <textarea name="quest_text" id="quest_text" rows="30" cols="30"
              placeholder="<%@include file="instruction.jsp" %>"></textarea>
    <button type="submit">Submit</button>

</form>


</body>
</html>
