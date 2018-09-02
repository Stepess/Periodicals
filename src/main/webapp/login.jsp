<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<fmt:bundle basename="pagecontent" prefix="label.">
    <div class="container">
        <div class="row vertical-offset-100 justify-content-center login">
            <div class="col-md-4 col-md-offset-4 well">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please sign in</h3>
                    </div>
                    <div class="panel-body">
                        <form accept-charset="UTF-8" role="form" name="loginForm" method="POST"
                              action="${pageContext.request.contextPath}/app/login">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="<fmt:message key="login"/>" name="login"
                                           type="text">
                                    <p class="text-danger">
                                            ${wrongLogin}
                                    </p>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="<fmt:message key="password"/>"
                                           name="password" type="password" value="">
                                    <p class="text-danger">
                                            ${wrongPassword}
                                    </p>
                                </div>
                                <input class="btn btn-lg btn-success btn-block" type="submit"
                                       value="<fmt:message key="sign.in"/>">
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
