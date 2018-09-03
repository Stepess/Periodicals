<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/adminMenu.jsp"/>
<fmt:bundle basename="pagecontent" prefix="field.">
    <div class="container vertical-tb-offset-10 min_height">
        <div class="row">
            <table class="table">
                <tr>
                    <th>
                        <fmt:message key="title"/>
                    </th>
                    <th>
                        <fmt:message key="quantity"/>
                    </th>
                </tr>
                <c:forEach items="${requestScope.statistics}" var="entry">
                    <tr>
                        <td><c:out value="${entry.key}"/></td>
                        <td><c:out value="${entry.value}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>