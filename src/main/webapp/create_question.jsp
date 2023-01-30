<%--
  Created by IntelliJ IDEA.
  User: idob2
  Date: 29.01.2023
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вопросы</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/CreateQuestionServlet" method="post">
    <input id="quest_text" name="quest_text">Введите вопрос</input>
    <input id="quest1_text" name="quest_text">Введите вопрос</input>
    <button type="submit">Submit</button>
    <label for="answers">Выберите количество ответов:</label>
    <select id="answers" onchange="setAnswers()">
        <option value='1'>1</option>
        <option value='2'>2</option>
        <option value='3'>3</option>
        <option value='4'>4</option>
    </select>
</form>

<script>
    function setAnswers(){

    }
</script>
</body>
</html>
