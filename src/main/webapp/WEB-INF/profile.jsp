<%@include file="pageConstructor/header.jsp"%>
My Profile
${sessionScope.user}
${sessionScope.user.getRole()}
<c:if test="${sessionScope.isAdmin}">
<a href="adminMenu">to Admin Panel</a>
</c:if>

<%@include file="pageConstructor/footer.jsp"%>