<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="ru">

<%@ include file="parts/head.jsp" %>

<body>

<main class="form-quest-init w-100 m-auto card position-relative">
    <div class="card card-opacity">
        <div class="card-body">
            <h5 class="card-title text-center text-info">Привет!</h5>
            <p class="card-text">
                Здесь тебе придется просто проделать длинный нелегкий путь из стартовой локации
                с загадочным названием <span style="color: #ec7a15"><b>Chamoia</b></span>
                в конечную <span style="color: #1f8915"><b>Akosia</b></span>.
            </p>
            <p class="card-text">
                В некоторых локациях тебе встретятся смотрители этих самых локаций, которые
                имеют влияние, авторитет и могут помочь с доступом к определенным закрытым локациям.
            </p>
            <p class="card-text"><small class="text-muted">Регистрируй свой username и вперед!!!</small></p>

            <form action="start" method="POST">
                <div class="input-group">
                    <input type="text" class="form-control" name="username" placeholder="Твой username (не более 20 символов)" maxlength="20" aria-label=""
                           value="" required>
                    <button class="btn btn-success" type="submit" name="startBtn" value="start">Стартуем</button>
                    <button class="btn btn-warning" type="submit" name="continueBtn" value="continue">Продолжаем
                    </button>
                    <button class="btn btn-danger" type="submit" name="removeBtn" value="remove">Удаляем</button>
                </div>
            </form>

            <p class="text-info text-start"><small>${tooltip}</small></p>
        </div>
        <img src="${pageContext.request.contextPath}/images/startpage-bg.png" class="card-img-bottom" alt="...">
    </div>
</main>

<%@ include file="parts/index_navbar.jsp" %>

<%@ include file="parts/users_list_modal.jsp" %>

<%@ include file="parts/description_modal.jsp" %>

<%@ include file="parts/common_scripts.jsp" %>

</body>
</html>
