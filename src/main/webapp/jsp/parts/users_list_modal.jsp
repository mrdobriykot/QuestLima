<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="usersListModal" tabindex="-1" aria-labelledby="usersListModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="usersListModalLabel">Список занятых username</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">USERNAME</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user" varStatus="loop">
                        <tr>
                            <th scope="row">${user.getId()}</th>
                            <td>${user.getName()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <p class="text-info text-start"><small>Список обнуляется после перезагрузки сервера</small></p>
            </div>
        </div>
    </div>
</div>
