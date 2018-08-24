<%--
  Created by IntelliJ IDEA.
  User: Senpai
  Date: 22.08.2018
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="pagecontent" prefix="label.">
    <p><fmt:message key="hello"/>, ${sessionScope.role}, ${sessionScope.login}</p>
</fmt:bundle>
<fmt:bundle basename="pagecontent" prefix="label.">
    <a href="${pageContext.request.contextPath}/app/logout">
        <fmt:message key="logout"/>
    </a>|
    <a href="${pageContext.request.contextPath}/index.jsp">
        <fmt:message key="main"/>
    </a>|
    <a href="${pageContext.request.contextPath}/app/catalog">
        <fmt:message key="catalog"/>
    </a>|
    <a href="${pageContext.request.contextPath}/app/subscriptions">
        <fmt:message key="subscription"/>
    </a>|
    <a href="${pageContext.request.contextPath}/app/payments">
        <fmt:message key="payment"/>
    </a>|
    <a href="${pageContext.request.contextPath}/admin/addPublication.jsp">
        <fmt:message key="add.publication"/>
    </a>
</fmt:bundle>


