<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<jsp:include page="/WEB-INF/component/mainBody.jsp"/>
<fmt:bundle basename="pagecontent" prefix="field.">
    <table border="1" cellpadding="5">
        <caption><h2>Payments List</h2></caption>
        <tr>
            <th>
                <fmt:message key="title"/>
            </th>
            <th>
                <fmt:message key="quantity"/>
            </th>

        </tr>

        <c:forEach items="${requestScope.report}" var="user">
            <tr>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.address}"/></td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>