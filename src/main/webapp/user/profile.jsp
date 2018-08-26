<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<c:out value="${user.login}"/>
<br/>
<fmt:message key="email"/><br/>
<c:out value="${user.email}"/>
<br/>
${wrongemail}
<br/>
<br/>
<fmt:message key="first.name"/><br/>
<c:out value="${user.firstName}"/>
<br/>
${wrongfirstName}
<br/>
<fmt:message key="last.name"/><br/>
<c:out value="${user.lastName}"/>
<br/>
<fmt:message key="country"/><br/>
<c:out value="${user.address}"/>
<br/>
</body>
</html>
