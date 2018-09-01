<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="pagecontent" prefix="label.">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">
                        <fmt:message key="main"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/catalog">
                        <fmt:message key="catalog"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/subscriptions">
                        <fmt:message key="subscription"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/payments">
                        <fmt:message key="payment"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/profile">
                        <fmt:message key="profile"/>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</fmt:bundle>