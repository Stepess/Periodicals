<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tagLib.tld" %>
<html>
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="payments"/>
        </fmt:bundle>
    </title>
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
<fmt:bundle basename="pagecontent" prefix="label.">
    <div class="container vertical-tb-offset-10  min_height">
        <div class="row">
            <c:choose>
                <c:when test="${not empty subscriptions}">
                    <table class="table">
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
                            <tr>
                                <td>
                                    <c:out value="${subscription.publication.title}"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${subscription.payment.bill}" type="currency"/>
                                </td>
                                <td>
                                    <ex:formatDateTime localDateTime="${subscription.payment.paymentDateTime}"
                                                       locale="${sessionScope.locale}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="container-fluid mt-3 font-weight-bold">
                        <p class="text-center text-danger">
                                ${fail}
                        </p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>
