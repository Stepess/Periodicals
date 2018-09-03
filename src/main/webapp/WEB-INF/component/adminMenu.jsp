<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="pagecontent" prefix="label.">
    <%--<a href="${pageContext.request.contextPath}/app/logout">
        <fmt:message key="logout"/>
    </a>|
    <a href="${pageContext.request.contextPath}/index.jsp">
        <fmt:message key="main"/>
    </a>|
    <a href="${pageContext.request.contextPath}/app/catalog">
        <fmt:message key="catalog"/>
    |
    <a href="${pageContext.request.contextPath}/admin/addPublication.jsp">
        <fmt:message key="add.publication"/>
    </a>
    |
    <a href="${pageContext.request.contextPath}/app/statistics">
        <fmt:message key="statistics"/>
    </a>
    |
--%>

    <nav class="navbar navbar-expand-lg navbar-light bg-light"><div class="container">
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/addPublication.jsp">
                        <fmt:message key="add.publication"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/statistics">
                        <fmt:message key="statistics"/>
                    </a>
                </li>
            </ul>
        </div></div>
    </nav>

</fmt:bundle>


