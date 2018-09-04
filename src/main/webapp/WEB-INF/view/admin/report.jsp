<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="report"/>
        </fmt:bundle>
    </title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/adminMenu.jsp"/>
<fmt:bundle basename="pagecontent" prefix="label.">
    <div class="container vertical-tb-offset-10 min_height">
        <div class="row">
        <table class="table">
            <caption><h2>Payments List</h2></caption>
            <tr>
                <th>
                    <fmt:message key="login"/>
                </th>
                <th>
                    <fmt:message key="first.name"/>
                </th>
                <th>
                    <fmt:message key="last.name"/>
                </th>
                <th>
                    <fmt:message key="email"/>
                </th>
                <th>
                    <fmt:message key="address"/>
                </th>
            </tr>
            <c:forEach items="${requestScope.report}" var="user">
                <tr>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.address}"/></td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </div>

</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>