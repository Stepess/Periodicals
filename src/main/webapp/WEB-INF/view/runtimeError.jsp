<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="oops"/>
        </fmt:bundle>
    </title>
</head>
<body>
<c:import url="/WEB-INF/component/header.jsp"/>
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
    <div class="page-wrap d-flex flex-row align-items-center min_height">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-12 text-center">
                    <span class="display-1 d-block"><fmt:message key="something.wrong"/> </span>
                    <div class="mb-4 lead">${pageContext.exception.message}</div>
                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-link"><fmt:message key="back.home"/></a>
                </div>
            </div>
        </div>
    </div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>

