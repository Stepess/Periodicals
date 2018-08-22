<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<h2>Hello, ${sessionScope.login}, ${sessionScope.role}!</h2>
<jsp:include page="/WEB-INF/component/header.jsp"></jsp:include>
<br/>
<c:if test="${sessionScope.role == 'guest'}">
    <a href="/${sessionScope.role}/main.jsp">Home</a>
</c:if>
</body>
</html>
