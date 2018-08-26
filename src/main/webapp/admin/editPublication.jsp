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


<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/editPublication">
    <input type="hidden" name="pubId" value="${param.pubId}"
    <fmt:message key="title_en"/><br/>
    <input type="text" name="title_en" value="${param.title_en}"/>
    <br/>
        ${wrongtitle_en}
    <fmt:message key="title_ua"/><br/>
    <input type="text" name="title_ua" value="${param.title_ua}"/>
    <br/>
        ${wrongtitle_ua}
    <fmt:message key="author"/><br/>
    <input type="text" name="author" value="${param.author}"/>
    <br/>
        ${wrongauthor}
    <fmt:message key="genre_en"/><br/>
    <input type="text" name="genre_en" value="${param.genre_en}"/>
    <br/>
        ${wronggenre_en}
    <br/>
    <fmt:message key="genre_ua"/><br/>
    <input type="text" name="genre_ua" value="${param.genre_ua}"/>
    <br/>
        ${wronggenre_ua}
    <br/>
    <fmt:message key="price"/><br/>
    <input type="number" step=".01" name="price" value="${param.price}"/>
    <br/>
        ${wrongprice}
    <br/>
    <fmt:message key="description_en"/><br/>
    <input type="text" name="description_en" value="${param.description_en}"/>
    <br/>
        ${wrongdescription_en}
    <br/>
    <fmt:message key="description_ua"/><br/>
    <input type="text" name="description_ua" value="${param.description_ua}"/>
    <br/>
        ${wrongdescription_ua}
    <br/>





    <br/>

    <input type="submit" value="<fmt:message key="add.publication"/>"/>
</form>



</fmt:bundle>
</body>
</html>