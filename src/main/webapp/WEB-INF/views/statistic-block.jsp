<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p class="w3-medium">
    <i>
        <b>Статистика</b><br/>
        количество игр: <c:out value="${statistic.gameCount}"></c:out>,
        количество вопросов: <c:out value="${statistic.questionCount}"></c:out> <br/>
    </i>
</p>
