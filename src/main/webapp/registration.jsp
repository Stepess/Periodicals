<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="pagecontent" prefix="label.">
<head>
    <title><fmt:message key="add.publication"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
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
<div class="container center-block">
    <div class="row vertical-offset-100 justify-content-center">
        <div class="col-md-4 col-md-offset-4 well">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign in</h3>
                </div>
                <div class="panel-body">
                    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/registration">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="login"/>" name="login"
                                       value="${param.login}" type="text" required>
                                <p class="text-danger">
                                        ${wronglogin}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="password"/>" name="password"
                                       type="password" required>
                                <p class="text-danger">
                                        ${wrongpassword}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="password"/>"
                                       name="passwordDuplicate" type="password" required>
                                <p class="text-danger">
                                        ${wrongpasswordDuplicate}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="email"/>"
                                       value="${param.email}" name="email" type="email" required>
                                <p class="text-danger">
                                        ${wrongemail}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="first.name"/>"
                                       value="${param.firstName}" name="firstName" type="text" required>
                                <p class="text-danger">
                                        ${wrongfirstName}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="last.name"/>"
                                       value="${param.lastName}" name="lastName" type="text" required>
                                <p class="text-danger">
                                        ${wronglastName}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder=" <fmt:message key="country"/>"
                                       value="${param.country}" name="country" type="text" required>
                                <p class="text-danger">
                                        ${wrongcountry}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="city"/>"
                                       value="${param.city}" name="city" type="text" required>
                                <p class="text-danger">
                                        ${wrongcity}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="street"/>"
                                       value="${param.street}" name="street" type="text" required>
                                <p class="text-danger">
                                        ${wrongstreet}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="building"/>"
                                       value="${param.building}" name="building" type="number" required>
                                <p class="text-danger">
                                        ${wrongbuilding}
                                </p>
                            </div>
                            <input type="submit" value="<fmt:message key="sign.up"/>"/>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>