<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">--%>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="pagecontent" prefix="label.">
    <header id="header" class="head">
        <div class="container">
            <div class="header__container align-items-center menu-href ">
                <div class="header__form">
                    <h1 class="head__title">
                        Periodicals
                    </h1>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.role == 'guest'}">
                        <div class="header__form2" style="padding-left: 100px;">
                            <div>
                                <a class="head__a" href="${pageContext.request.contextPath}/guest/login">
                                    <fmt:message key="sign.in"/>
                                </a>
                                <a style="padding-left: 15px;" class="head__a"
                                   href="${pageContext.request.contextPath}/guest/registration">
                                    <fmt:message key="sign.up"/>
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="header__form2" style="padding-left: 100px;">
                            <div>
                                <p class="head__a" style="display:inline">
                                        ${sessionScope.role}, ${sessionScope.login}
                                </p>
                                <a class="head__a" style="padding-left: 15px;"
                                   href="${pageContext.request.contextPath}/${sessionScope.role}/logout">
                                    <fmt:message key="logout"/>
                                </a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="  header__form3 ">
                    <form <%--onsubmit="location.reload(true)--%>
                            method="post"
                            action="${pageContext.request.contextPath}/${sessionScope.role}/changeLanguage">
                        <input type="hidden" name="command" value="${pageContext.request.requestURI}"/>
                        <input type="hidden" name="command1" value="${pageContext.request.requestURL}"/>
                        <input type="hidden" name="query" value="${pageContext.request.queryString}"/>
                        <select class="form-control" id="language" name="language" onchange="submit()">
                            <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
                            <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
                        </select>
                    </form>
                </div>
            </div>
        </div>
    </header>
</fmt:bundle>
</body>
</html>


