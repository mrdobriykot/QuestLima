<%@include file="../pageConstructor/header.jsp"%>

<c:if test="${!sessionScope.isAdmin}">
    Sorry, but you are not admin =)

    You are hacker!

</c:if>

<c:if test="${sessionScope.isAdmin}">
ADMIN PANEL
    <br>
    <a href="users">All Users</a>

</c:if>
<%@include file="../pageConstructor/footer.jsp"%>