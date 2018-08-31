<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<c:addSubscription var="language" value="${not empty param.language ? param.language : 'en_US'}" scope="session"/>--%>
<html>
<head>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script src="js/bootstrap.min.js"></script>--%>
        <link href="css/style.css" rel="stylesheet">
</head>
<body>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<form onsubmit="location.reload(true)">
    <select id="language" name="language" onchange="submit()">
        <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<%--<c:addSubscription var="language" value="${not empty param.language ? param.language : 'en_US'}" scope="session"/>--%>
<fmt:bundle basename="pagecontent" prefix="label.">
    <c:if test="${sessionScope.role == 'guest'}">

<div class="container">
    <div class="row">
        <div class="col-8">
            kek
        </div>

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

        <a href="#" data-toggle="modal" data-target="#login-modal">Login</a>

        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Login to Your Account</h1><br>
                    <form>
                        <input type="text" name="user" placeholder="Username">
                        <input type="password" name="pass" placeholder="Password">
                        <input type="submit" name="login" class="login loginmodal-submit" value="Login">
                    </form>

                    <div class="login-help">
                        <a href="#">Register</a> - <a href="#">Forgot Password</a>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</fmt:bundle>

</body>
</html>



