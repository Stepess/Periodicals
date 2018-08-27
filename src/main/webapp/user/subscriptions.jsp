
<%--
  Created by IntelliJ IDEA.
  User: Senpai
  Date: 22.08.2018
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Subscriptions</title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/userMenu.jsp"/>
${fail}
${success}
<fmt:bundle basename="pagecontent" prefix="field.">
<table border="1" cellpadding="5">
    <caption><h2>Subscription List</h2></caption>
    <tr>
        <th>
            <fmt:message key="title"/>
        </th>
        <th>
            <fmt:message key="state"/>
        </th>
        <th>
            <fmt:message key="start.date"/>
        </th>
        <th>
            <fmt:message key="end.date"/>
        </th>
        <th>
            <fmt:message key="price"/>
        </th>


    </tr>

    <c:forEach items="${requestScope.subscriptions}" var="subscription">
        <tr>
            <td><c:out value="${subscription.publication.title}"/></td>
            <td><c:out value="${subscription.state}"/></td>
            <td><c:out value="${subscription.startDate}"/></td>
            <td><c:out value="${subscription.endDate}"/></td>
            <td><c:out value="${subscription.total}"/> </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/app/pay">
                    <input type="hidden" name="subId" value="${subscription.id}">
                    <input type="submit" value="<fmt:message key="pay"/>">
                </form>
            </td>


                <%--<td>
                    <a href="/delete?name=<c:out value='${image.name}' />">Delete</a>
                </td>
                <td>
                    <input type="checkbox" name="${image.name}" >
                </td>--%>
        </tr>
    </c:forEach>
</table>
</fmt:bundle>
</body>
</html>
