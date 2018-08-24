<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="pagecontent" prefix="label.">
<head>
    <title><fmt:message key="add.publication"/> </title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/mainBody.jsp"/>


<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/addPublication">
    <fmt:message key="title_en"/><br/>
    <input type="text" name="title_en" value="${requestScope.publication.title}"/>
    <br/>
    <fmt:message key="title_ua"/><br/>
    <input type="text" name="title_ua" value=""/>
    <br/>
    <fmt:message key="author"/><br/>
    <input type="text" name="author" value=""/>
    <br/>
    <fmt:message key="genre_en"/><br/>
    <input type="text" name="genre_en" value=""/>
    <br/>
    <fmt:message key="genre_ua"/><br/>
    <input type="text" name="genre_ua" value=""/>
    <br/>
    <fmt:message key="price"/><br/>
    <input type="number" step=".01" name="price" value=""/>
    <br/>
    <fmt:message key="description_en"/><br/>
    <input type="text" name="description_en" value=""/>
    <br/>
    <fmt:message key="description_ua"/><br/>
    <input type="text" name="description_ua" value=""/>
    <br/>



    <br/>

    <input type="submit" value="<fmt:message key="add.publication"/>"/>
</form>



</fmt:bundle>
</body>
</html>