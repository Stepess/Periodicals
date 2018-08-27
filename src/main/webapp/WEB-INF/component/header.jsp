<%--
  Created by IntelliJ IDEA.
  User: Senpai
  Date: 18.08.2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<c:set var="language" value="${not empty param.language ? param.language : 'en_US'}" scope="session"/>--%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
</head>
</html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}" scope="session" />

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<%--<c:set var="language" value="${not empty param.language ? param.language : 'en_US'}" scope="session"/>--%>
<fmt:bundle basename="pagecontent" prefix="label.">
<c:if test="${sessionScope.role == 'guest'}">

        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/login">
            <fmt:message key="login"/><br/>
            <input type="text" name="login" value=""/>
            <br/>
            <fmt:message key="password"/><br/>
            <input type="password" name="password" value=""/>
            <br/>
                ${errorLoginPassMessage}
            <br/>
            <input type="submit" value="Log in"/>
        </form>

    <br/>
    <a href="registration.jsp">
        <fmt:message key="sign.up"/>
    </a>


</c:if>
</fmt:bundle>

