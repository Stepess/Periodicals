<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tagLib.tld" %>
<html>
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="subscriptions"/>
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
    <div class="container vertical-tb-offset-10 min_height">
        <div class="row">
            <form method="get" action="${pageContext.request.contextPath}/user/subscriptions">
                <select class="form-control" name="state" onchange="submit()">
                    <c:forEach var="state" items="${requestScope.states}">
                        <c:choose>
                            <c:when test="${chosen_state==state}">
                                <option value="${state}" selected><fmt:message key="${state}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="${state}"><fmt:message key="${state}"/></option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </form>
        </div>
        <c:choose>
        <c:when test="${not empty subscriptions}">
        <div class="row">
            <div class="container-fluid">
                <p class="text-center text-success">${success}</p>
                <p class="text-center text-danger">${fail}</p>
            </div>
        </div>
        <div class="row">
            <c:choose>
                <c:when test="${chosen_state=='PAID'}">
                    <div class="container">
                        <table class="table">
                            <tr>
                                <th>
                                    <fmt:message key="title"/>
                                </th>
                                <th>
                                    <fmt:message key="genre"/>
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
                                    <td><c:out value="${subscription.publication.genre}"/></td>
                                    <td><ex:formatDate localDate="${subscription.startDate}"
                                                       locale="${sessionScope.locale}"/></td>
                                    <td><ex:formatDate localDate="${subscription.endDate}"
                                                       locale="${sessionScope.locale}"/></td>
                                    <td><ex:formatCurrency money="${subscription.payment.bill}"
                                                           locale="${sessionScope.locale}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container">
                        <table class="table">
                            <tr>
                                <th>
                                    <fmt:message key="title"/>
                                </th>
                                <th>
                                    <fmt:message key="genre"/>
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
                                <th>
                                    <fmt:message key="pay"/>
                                </th>
                                <th>
                                    <fmt:message key="delete"/>
                                </th>
                            </tr>
                            <c:forEach items="${requestScope.subscriptions}" var="subscription">
                                <tr>
                                    <td><c:out value="${subscription.publication.title}"/></td>
                                    <td><c:out value="${subscription.publication.genre}"/></td>
                                    <td><ex:formatDate localDate="${subscription.startDate}"
                                                       locale="${sessionScope.locale}"/></td>
                                    <td><ex:formatDate localDate="${subscription.endDate}"
                                                       locale="${sessionScope.locale}"/></td>
                                    <td><ex:formatCurrency money="${subscription.payment.bill}"
                                                           locale="${sessionScope.locale}"/></td>
                                    <td>
                                        <form method="POST" action="${pageContext.request.contextPath}/user/pay">
                                            <input type="hidden" name="subId" value="${subscription.id}">
                                            <input type="submit" value="<fmt:message key="pay"/>">
                                        </form>
                                    </td>
                                    <td>
                                        <form method="POST"
                                              action="${pageContext.request.contextPath}/user/deleteSubscription">
                                            <input type="hidden" name="subId" value="${subscription.id}">
                                            <input type="submit" value="<fmt:message key="delete"/>">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
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
