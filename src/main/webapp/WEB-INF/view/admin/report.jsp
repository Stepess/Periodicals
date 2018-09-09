<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <c:choose>
            <c:when test="${not empty fail}">
                <div class="container-fluid mt-3 font-weight-bold">
                    <p class="text-center text-danger">
                            ${fail}
                    </p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <table class="table">
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
            </c:otherwise>
        </c:choose>
    </div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>