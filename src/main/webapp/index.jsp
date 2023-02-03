<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"--%>
<%--          rel="stylesheet"--%>
<%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"--%>
<%--          crossorigin="anonymous">--%>
<%--</head>--%>
<%--<body>--%>
<%@include file="WEB-INF/parts/header.jsp"%>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="users">user</a>


<%@include file="WEB-INF/parts/footer.jsp"%>