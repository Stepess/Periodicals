
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

<%@ taglib prefix = "ex" uri = "/WEB-INF/tagLib.tld"%>

<html>
<head>
    <title>Subscriptions</title>
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
    <div class="container min_height">
        <div class="row vertical-tb-offset-10">
        <form method="get" action="${pageContext.request.contextPath}/app/subscriptions">
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
        <div class="row">
            <div class="container">
                <p class="text-center text-success">${success}</p>
                <p class="text-center text-danger">${fail}</p>
            </div>
        </div>
        <div class="row">
    <c:choose>
        <c:when test="${chosen_state=='PAID'}">
            <div class="container">
                <table class="table">
                    <caption><h2>Subscription List</h2></caption>
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
                            <td><ex:formatDate localDate="${subscription.startDate}" locale="${sessionScope.locale}"/></td>
                            <td><ex:formatDate localDate="${subscription.endDate}" locale="${sessionScope.locale}"/></td>
                            <td><fmt:formatNumber value="${subscription.payment.bill}" type="currency"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <table class="table">
                    <caption><h2>Subscription List</h2></caption>
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
                            <fmt:message key="action"/>
                        </th>
                    </tr>

                    <c:forEach items="${requestScope.subscriptions}" var="subscription">
                        <tr>
                            <td><c:out value="${subscription.publication.title}"/></td>
                            <td><c:out value="${subscription.publication.genre}"/></td>
                            <td><ex:formatDate localDate="${subscription.startDate}" locale="${sessionScope.locale}"/></td>
                            <td><ex:formatDate localDate="${subscription.endDate}" locale="${sessionScope.locale}"/></td>
                            <td><fmt:formatNumber value="${subscription.payment.bill}" type="currency"/></td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/app/pay">
                                    <input type="hidden" name="subId" value="${subscription.id}">
                                    <input type="submit" value="<fmt:message key="pay"/>">
                                </form>
                            </td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/app/deleteSubscription">
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
        </div>
    </div>


</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>
