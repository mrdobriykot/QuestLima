<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="ru">

<%@ include file="parts/head.jsp" %>

<body>

<main class="form-quest-init w-100 m-auto card py-2">
    <div class="card-body">
        <div class="container text-center">
            <div class="row mb-2">
                <div class="col-md-4">
                    <%-- user block --%>
                    <div class="card border-primary text-bg-primary card-opacity block-shadow mb-3 h-100"
                         style="width: 16rem;">
                        <div class="card-body">
                            <img src="${pageContext.request.contextPath}/images/user-ava.png"
                                 class="card-img-bottom card-opacity"
                                 alt="">
                            <span class="position-absolute top-0 start-50 translate-middle badge rounded-pill bg-primary">Ваш ник: <br>${userName.toUpperCase()}</span>
                        </div>
                    </div>
                    <%-- end --%>
                </div>
                <div class="col-md-4">
                    <%-- current location block --%>
                    <div class="card card-opacity bg-none mb-3 h-100" style="width: 16rem;">
                        <img src="${pageContext.request.contextPath}/images/locations/${currentLocation.getName().toLowerCase()}.png"
                             class="card-img-top" alt="">
                        <div class="card-body">
                            <button type="button"
                                    class="btn btn-dark position-relative btn-location-width">${currentLocation.getName().toUpperCase()}</button>
                            <span class="position-absolute top-0 start-50 translate-middle badge rounded-pill bg-danger">текущая локация</span>
                        </div>
                    </div>
                    <%-- end --%>
                </div>
                <div class="col-md-4">
                    <%-- person on location block --%>
                    <form action="person" method="POST">
                        <div class="card border-success text-bg-success mb-3 card-opacity block-shadow"
                             style="width: 16rem;">
                            <div class="card-header">
                                Смотритель локации
                            </div>
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${personsOnLocation}" var="person" varStatus="loop">
                                    <li class="list-group-item border-success text-start">
                                        <img src="${pageContext.request.contextPath}/images/persons/${person.getName().toLowerCase()}.png"
                                             alt=""
                                             class="rounded"
                                             width="80"
                                             height="80">
                                        <button type="button"
                                                class="btn btn-success btn-sm">${person.getName()}</button>
                                        <input type="hidden" name="personId"
                                               value="${person.getId()}">

                                        <c:if test="${question != null}">
                                            <p class="h6 fw-bold mt-2 fst-italic text-info">${question.getText()}</p>
                                        </c:if>

                                        <c:forEach items="${answers}" var="answer" varStatus="loop">
                                            <div class="form-check">
                                                <input class="form-check-input"
                                                       type="radio" name="answerId"
                                                       value="${answer.getId()}"
                                                       <c:if test="${loop.index == 0}">checked</c:if>>
                                                <label class="form-check-label fw-normal h6 fst-italic fs-6">
                                                        ${answer.getText()}
                                                </label>
                                            </div>
                                        </c:forEach>

                                        <c:set value="${question.getType().toString() == 'CONTINUE'}"
                                               var="questionContinue"/>
                                        <button type="submit"
                                                class="btn btn-outline-success d-block mt-1 btn-sm"
                                                name="person"
                                                value="${person.getId()}">
                                            <c:out value="${questionContinue ? 'Ответить': 'Говорить'}"/>
                                        </button>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </form>
                    <%-- end --%>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col-md-4">
                    <div class="card border-primary text-bg-primary mb-3 card-opacity opacity-25 block-shadow"
                         style="width: 16rem;">
                        <div class="card-header border-primary">
                            Список моего инвентаря
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item border-primary text-primary">В разработке</li>
                            <li class="list-group-item border-primary text-primary">В разработке</li>
                            <li class="list-group-item border-primary text-primary">В разработке</li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <%-- possible directions of movement block --%>
                    <form action="action" method="POST">
                        <div class="card border-danger text-bg-danger mb-3 card-opacity block-shadow"
                             style="width: 16rem;">
                            <div class="card-header">
                                Возможные направления
                            </div>
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${locationsForMovement}" var="locationForMovement" varStatus="loop">
                                    <li class="list-group-item border border-0">
                                        <button type="submit" name="locationId"
                                                class="btn btn-danger btn-location-width position-relative"
                                                value="${locationForMovement.getId()}"
                                                <c:if test="${locationForMovement.isBlock()}">
                                                    title="Закрыто"
                                                    disabled
                                                </c:if>>
                                                <span class="position-absolute top-50 translate-middle"
                                                      style="left: 20px">
                                                    <img src="${pageContext.request.contextPath}/images/locations/${locationForMovement.getName().toLowerCase()}.png"
                                                         class="" width="30" height="30">
                                                </span>
                                            <span>${locationForMovement.getName()}</span>

                                            <c:if test="${locationForMovement.isBlock()}">
                                                <span class="position-absolute top-50 end-0 translate-middle">
                                                    <%@ include file="parts/svg/lock_icon.jsp" %>
                                                </span>
                                            </c:if>
                                            <c:if test="${!locationForMovement.isBlock()}">
                                                <span class="position-absolute top-50 end-0 translate-middle">
                                                    <%@ include file="parts/svg/unlock_icon.jsp" %>
                                                </span>
                                            </c:if>

                                        </button>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </form>
                    <%-- end --%>
                </div>
                <div class="col-md-4">
                    <div class="card border-success text-bg-success mb-3 card-opacity opacity-25 block-shadow"
                         style="width: 16rem;">
                        <div class="card-header">
                            Список предметов локации
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item border-success text-success">В разработке</li>
                            <li class="list-group-item border-success text-success">В разработке</li>
                            <li class="list-group-item border-success text-success">В разработке</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="parts/action_navbar.jsp" %>

<%@ include file="parts/world_map_modal.jsp" %>

<%@ include file="parts/description_modal.jsp" %>

<%@ include file="parts/common_scripts.jsp" %>

<c:if test="${win == true}">
    <%@ include file="parts/win_modal.jsp" %>
</c:if>

</body>
</html>
