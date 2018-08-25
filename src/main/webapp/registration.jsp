<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="pagecontent" prefix="label.">
<head>
    <title><fmt:message key="add.publication"/> </title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>



<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/registration">
    <fmt:message key="login"/><br/>
    <input type="text" name="login" value="${param.login}" required/>
    ${wronglogin}
    <br/>
    <fmt:message key="password"/><br/>
    <input type="password" name="password" value="" required/>
    ${wrongpassword}
    <br/>
    <fmt:message key="password"/><br/>
    <input type="password" name="passwordDuplicate" value="" required/>
    <br/>
        ${wrongpasswordDuplicate}
    <br/>
    <fmt:message key="email"/><br/>
    <input type="text" name="email" value="${param.email}" required/>
    <br/>
    ${wrongemail}
    <br/>
    <fmt:message key="first.name"/><br/>
    <input type="text" name="firstName" value="${param.firstName}" required/>
    <br/>
    ${wrongfirstName}
    <br/>
    <fmt:message key="last.name"/><br/>
    <input type="text" name="lastName" value="${param.lastName}" required/>
    <br/>
    ${wronglastName}
    <br/>
    <%--<fmt:message key="address"/><br/>
    <input type="text" name="address" value=""/>
    <br/>--%>
    <fmt:message key="country"/><br/>
    <input type="text" name="country" value="${param.country}" required/>
    <br/>
    ${wrongcountry}
    <br/>
    <fmt:message key="city"/><br/>
    <input type="text" name="city" value="${param.city}" required/>
    <br/>
    ${wrongcity}
    <br/>
    <fmt:message key="street"/><br/>
    <input type="text" name="street" value="${param.street}" required/>
    <br/>
    ${wrongstreet}
    <br/>
    <fmt:message key="building"/><br/>
    <input type="number" name="building" value="${param.building}" required/>
    <br/>
    ${wrongbuilding}


    <%--<fmt:message key="description_ua"/><br/>
    <input type="text" name="description_ua" value=""/>
    <br/>--%>



    <br/>

    <input type="submit" value="<fmt:message key="sign.up"/>"/>
</form>



</fmt:bundle>
</body>
</html>