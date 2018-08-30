<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix = "ex" uri = "/WEB-INF/tagLib.tld"%>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/userMenu.jsp"/>
<fmt:bundle basename="pagecontent" prefix="field.">
    <table border="1" cellpadding="5">
        <caption><h2>Payments List</h2></caption>
        <tr>
            <th>
                <fmt:message key="title"/>
            </th>
            <th>
                <fmt:message key="bill"/>
            </th>
            <th>
                <fmt:message key="payment.date.time"/>
            </th>

        </tr>

        <c:forEach items="${requestScope.subscriptions}" var="subscription">
            <c:if test="${not empty subscription.payment.paymentDateTime}">
            <tr>
                <td><c:out value="${subscription.publication.title}"/></td>
                <td><fmt:formatNumber value="${subscription.payment.bill}"  type="currency"/></td>
               <%-- <td><c:out value="${subscription.payment.paymentDateTime}"/></td>--%>
                <td><ex:formatDateTime localDateTime="${subscription.payment.paymentDateTime}" locale="${sessionScope.locale}"/></td>

                    <%--<td>
                        <a href="/deletePublication?name=<c:out value='${image.name}' />">Delete</a>
                    </td>
                    <td>
                        <input type="checkbox" name="${image.name}" >
                    </td>--%>
            </tr>
            </c:if>
        </c:forEach>
    </table>
</fmt:bundle>

</body>
</html>
