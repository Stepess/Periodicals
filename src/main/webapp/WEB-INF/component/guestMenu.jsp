<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="pagecontent" prefix="label.">

<nav class="navbar navbar-expand-lg navbar-light bg-light"> <div class="container">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">
                    <fmt:message key="main"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/guest/catalog">
                    <fmt:message key="catalog"/>
                </a>
            </li>
        </ul>
    </div></div>
</nav>

</fmt:bundle>