<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/userMenu.jsp"/>
<fmt:bundle basename="pagecontent" prefix="label.">
    <br/><fmt:message key="login"/><br/>
<c:out value="${user.login}"/>
<br/>
<fmt:message key="email"/><br/>
<c:out value="${user.email}"/>
<br/>


<fmt:message key="first.name"/><br/>
<c:out value="${user.firstName}"/>
<br/>

<fmt:message key="last.name"/><br/>
<c:out value="${user.lastName}"/>
<br/>
<fmt:message key="address"/><br/>
<c:out value="${user.address}"/>
<br/>
<fmt:message key="account"/><br/>
    <c:out value="${user.account}"/>
    <br/>
    <form method="POST" action="app/replenish">
        <input type="number" name="money">
        <input type="submit" value="<fmt:message key="replenish"/>"/>
    </form>
</fmt:bundle>
</body>
</html>
