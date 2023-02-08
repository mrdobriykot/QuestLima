<%@include file="pageConstructor/header.jsp"%>
My Profile
<br>
<br>
Your name: ${sessionScope.user.getUserName()}
<br>
Your Role: ${sessionScope.user.getRole()}
<br>
Your count of game: ${sessionScope.user.getGameList().size()}
<br>
<a href="userChangeProfile">Change your profile
<br>
<br>
<c:if test="${sessionScope.isAdmin}">
<a href="adminMenu">to Admin Panel</a>
</c:if>

<%@include file="pageConstructor/footer.jsp"%>