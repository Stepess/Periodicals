<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="pagecontent" prefix="label.">



    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <div class="collapse navbar-collapse" id="navbarNavDropdown">

                <ul class="navbar-nav ">

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">
                                <fmt:message key="main"/>
                            </a>
                        </li>


                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/catalog">
                                <fmt:message key="catalog"/>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/subscriptions">
                                <fmt:message key="subscription"/>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/payments">
                                <fmt:message key="payment"/>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/profile">
                                <fmt:message key="profile"/>
                            </a>
                        </li>


                </ul>

            </div>
        </div>
    </nav>
  <%--  <div class="container-fluid">
<div class="container">
    <div class="menu-href">
        <div>
            <a  href="${pageContext.request.contextPath}/index.jsp">
                <fmt:message key="main"/>
            </a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/app/catalog">
                <fmt:message key="catalog"/>
            </a>
        </div>
        <div>
            <a  href="${pageContext.request.contextPath}/app/subscriptions">
                <fmt:message key="subscription"/>
            </a>
        </div>
        <div>
            <a  href="${pageContext.request.contextPath}/app/payments">
                <fmt:message key="payment"/>
            </a>

        </div>
        <div>
            <a  href="${pageContext.request.contextPath}/app/profile">
                <fmt:message key="profile"/>
            </a>

        </div>

    </div>
</div>
    </div>--%>

 <%--   <ul class="nav nav-pills nav-fill">
        <li class="nav-item">
            <a class="nav-link active" href="#">Active</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Longer nav link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="#">Disabled</a>
        </li>
    </ul>
--%>



</fmt:bundle>



<%--
            --%>