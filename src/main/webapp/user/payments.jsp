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
<c:choose>
    <c:when test="${sessionScope.role=='admin'}">
        <jsp:include page="/WEB-INF/component/adminMenu.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role=='user'}">
        <jsp:include page="/WEB-INF/component/userMenu.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/component/guestMenu.jsp"/>
    </c:otherwise>
</c:choose>
<fmt:bundle basename="pagecontent" prefix="field.">
    <div class="container vertical-tb-offset-10  min_height" >
        <div class="row">
            <table class="table">
                <h2>Payments List</h2>
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
                    <%--<c:if test="${not empty subscription.payment.paymentDateTime}">--%>
                    <tr>
                        <td><c:out value="${subscription.publication.title}"/></td>
                        <td><fmt:formatNumber value="${subscription.payment.bill}"  type="currency"/></td>
                        <td><ex:formatDateTime localDateTime="${subscription.payment.paymentDateTime}" locale="${sessionScope.locale}"/></td>
                    </tr>
                    <%--</c:if>--%>
                </c:forEach>
            </table>
        </div>
    </div>

</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>
