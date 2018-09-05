<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<c:addSubscription var="language" value="${not empty param.language ? param.language : 'en_US'}" scope="session"/>--%>
<html>
<head>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
  <%--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script src="js/bootstrap.min.js"></script>--%>
        <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>

<%--<c:addSubscription var="language" value="${not empty param.language ? param.language : 'en_US'}" scope="session"/>--%>
<fmt:bundle basename="pagecontent" prefix="label.">

        <%--<a href="#" data-toggle="modal" data-target="#login-modal">Login</a>--%>

        <%--<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Login to Your Account</h1><br>
                    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/login">
                    <input type="text" name="login" placeholder="<fmt:message key="login"/>">
                            ${wrongLogin}
                        <input type="password" name="password" placeholder="<fmt:message key="password"/>">
                            ${wrongPassword}
                        <input type="submit" name="login" class="login loginmodal-submit" value="Login">
                    </form>

                    <div class="login-help">
                        <a href="registration.jsp"><fmt:message key="sign.up"/></a>
                    </div>
                </div>
            </div>
        </div>--%>

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
                        <div class="col-lg-3 ml-auto">
                            <nav>
                                <ul class="menu d-flex flex-row">
                                    <li class="menu__item">
                                        <a href="${pageContext.request.contextPath}/guest/login">
                                            <fmt:message key="sign.in"/>
                                        </a>
                                    </li>
                                    <li class="menu__item">
                                        <a href="${pageContext.request.contextPath}/guest/registration">
                                            <fmt:message key="sign.up"/>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </c:when>
                    <c:otherwise>
                <div class="header__form2" style="padding-left: 100px;">
                    <div>
                            <a class="head__a">
                                    ${sessionScope.role}    ${sessionScope.login}
                            </a>





                        <a class="head__a" style="padding-left: 10px;" href="${pageContext.request.contextPath}/${sessionScope.role}/logout">
                            <fmt:message key="logout"/>
                        </a>
                    </div>




                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="  header__form3 ">
                    <form <%--onsubmit="location.reload(true)--%> method="post" <%--action="${pageContext.request.contextPath}/${sessionScope.role}/changeLanguage"--%> >
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


   <%-- <c:if test="${sessionScope.role == 'guest'}">
        <div class="container">
            <div class="row">


                <div class="col-4">
                    <div class="input-group">
                        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/login">
                            <fmt:message key="login"/><br/>
                            <input type="text" name="login" value=""/>
                            <br/>
                                ${wrongLogin}
                            <br/>
                            <fmt:message key="password"/><br/>
                            <input type="password" name="password" value=""/>
                            <br/>
                                ${wrongPassword}
                            <br/>
                            <input type="submit" value="Log in"/>
                        </form>

                        <br/>
                        <a href="registration.jsp">
                            <fmt:message key="sign.up"/>
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </c:if>
--%>


</fmt:bundle>

</body>
</html>


<%--<header id="header" class="head">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <h1 class="head__title">
                        Periodicals
                    </h1>
                </div>
                <div class="col-lg-2">
                </div>

                <c:choose>
                    <c:when test="${sessionScope.role == 'guest'}">
                        <div class="col-lg-3 ml-auto">
                            <nav>
                                <ul class="menu d-flex flex-row">
                                    <li class="menu__item">
                                        <a href="${pageContext.request.contextPath}/guest/login">
                                            <fmt:message key="sign.in"/>
                                        </a>
                                    </li>
                                    <li class="menu__item">
                                        <a href="${pageContext.request.contextPath}/guest/registration">
                                            <fmt:message key="sign.up"/>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-lg-3 ml-auto">
                            <nav>
                                <ul class="menu d-flex flex-row">
                                    <li class="menu__item">
                                        <p>
                                            ${sessionScope.role}    ${sessionScope.login}
                                        </p>
                                    </li>
                                    <li class="menu__item">
                                        <a href="${pageContext.request.contextPath}/${sessionScope.role}/logout">
                                            <fmt:message key="logout"/>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </c:otherwise>
                </c:choose>
                <P>
                        ${requestScope.javax.servlet.forward.request_uri}
                </P>
                <div class="col-lg-1 ml-auto">
                    <form <%--onsubmit="location.reload(true)--%> <%--method="post"--%> <%--action="${pageContext.request.contextPath}/${sessionScope.role}/changeLanguage"--%><%-- >--%>
<%--<input type="hidden" name="command" value="${pageContext.request.requestURI}"/>
<input type="hidden" name="command1" value="${pageContext.request.requestURL}"/>
<input type="hidden" name="query" value="${pageContext.request.queryString}"/>
<select id="language" name="language" onchange="submit()">

    <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
    <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
</select>
</form>
</div>
</div>
</div>
</header>--%>

