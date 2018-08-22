<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<c:if test="${sessionScope.role != 'guest'}">
    <a href="/${sessionScope.role}/main.jsp">
        <fmt:bundle basename="pagecontent" prefix="label.">
            <fmt:message key="home"/>
        </fmt:bundle>
    </a>
</c:if>

<h1>INFO ABOUT SITE</h1>
</body>
</html>
