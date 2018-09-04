<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="profile"/>
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
            <table class="table">
                <tbody>
                <tr>
                    <th>
                        <fmt:message key="login"/>
                    </th>
                    <th>
                        <c:out value="${user.login}"/>
                    </th>
                </tr>
                <tr>
                    <th>
                        <fmt:message key="email"/>
                    </th>
                    <th>
                        <c:out value="${user.email}"/>
                    </th>
                </tr>
                <tr>
                    <th>
                        <fmt:message key="first.name"/>
                    </th>
                    <th>
                        <c:out value="${user.firstName}"/>
                    </th>
                </tr>
                <tr>
                    <th>
                        <fmt:message key="last.name"/>
                    </th>
                    <th>
                        <c:out value="${user.lastName}"/>
                    </th>
                </tr>
                <tr>
                    <th>
                        <fmt:message key="address"/>
                    </th>
                    <th>
                        <c:out value="${user.address}"/>
                    </th>
                </tr>
                <tr>
                    <th>
                        <fmt:message key="account"/><br/>
                    </th>
                    <th>
                        <fmt:formatNumber value="${user.account}" type="currency"/>
                    </th>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div class="container vertical-tb-offset-10">
                <form method="POST" action="${pageContext.request.contextPath}/${sessionScope.role}/replenish">
                    <div class="form-row">
                        <div class="col">
                            <input type="number" name="money">
                            <input class="btn btn-success" type="submit" value="<fmt:message key="replenish"/>"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>
