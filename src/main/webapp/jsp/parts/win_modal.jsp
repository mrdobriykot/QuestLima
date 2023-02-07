<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .bg-win-modal {
        background-image: url('${pageContext.request.contextPath}/images/win.gif');
        background-repeat: no-repeat;
        background-size: cover;
        object-fit: cover;
        height: 60vh;
        width: 90vw;
    }
</style>

<div class="modal fade" id="winModal" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="ewinModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content bg-win-modal">
            <div class="modal-header border-0">
<%--                <h5 class="modal-title text-light" id="winModalLabel">ОТЛИЧНО!!! Дело сделано!</h5>--%>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body"></div>
            <form action="${pageContext.request.contextPath}/restart" method="POST">
                <div class="modal-footer border-0 justify-content-center">
    <%--                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>--%>
                    <input type="hidden" name="locationId" value="1">
                    <button type="submit" class="btn btn-dark btn-lg">Начать сначала</button>
                </div>
            </form>
        </div>
    </div>
</div>
