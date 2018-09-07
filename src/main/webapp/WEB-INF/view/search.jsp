<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tagLib.tld" %>
<html>
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="search"/>
        </fmt:bundle>
    </title>
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
    <div class="container vertical-tb-offset-10 min_height">
        <div class="row">
        <form method="get" action="${pageContext.request.contextPath}/${sessionScope.role}/search">
            <div class="form-row">
                <div class="col">
                    <input name="title" type="text" class="form-control" placeholder="<fmt:message key="title"/>">
                </div>
                <div class="col">
                    <input name="genre" type="text" class="form-control" placeholder="<fmt:message key="genre"/>">
                </div>
                <div class="col">
                    <input name="leftPriceBoundary" type="number" class="form-control"
                           placeholder="<fmt:message key="left.price.boundary"/>">
                </div>
                <div class="col">
                    <input name="rightPriceBoundary" type="number" class="form-control"
                           placeholder="<fmt:message key="right.price.boundary"/>">
                </div>
                <div class="col">
                    <input class="btn btn-success" type="submit" value="<fmt:message key="search"/>"/>
                </div>

            </div>
            <%--<div class="container">
                <p class="text-danger">
                        ${fail}
                </p>
            </div>--%>
        </form>
        </div>
        <div class="row">
        <form action="${pageContext.request.contextPath}/${sessionScope.role}/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}"
              onchange="submit()">
            <input type="hidden" name="currentPage" value="1">
            <label for="records">Select records per page:</label>
            <select class="form-control" id="records" name="recordsPerPage">
                <c:choose>
                    <c:when test="${paginationParameters.recordsPerPage==5}">
                        <option value="5" selected>5</option>
                    </c:when>
                    <c:otherwise>
                        <option value="5">5</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${paginationParameters.recordsPerPage==10}">
                        <option value="10" selected>10</option>
                    </c:when>
                    <c:otherwise>
                        <option value="10">10</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${paginationParameters.recordsPerPage==15}">
                        <option value="15" selected>15</option>
                    </c:when>
                    <c:otherwise>
                        <option value="15">15</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </form>
        </div>
    <div class="row">
        <c:choose>
            <c:when test="${not empty publications}">
                <table class="table">
                    <tr>
                        <th>
                            <fmt:message key="title"/>
                        </th>
                        <th>
                            <fmt:message key="author"/>
                        </th>
                        <th>
                            <fmt:message key="genre"/>
                        </th>
                        <th>
                            <fmt:message key="price"/>
                        </th>
                        <th>
                            <fmt:message key="description"/>
                        </th>
                        <c:choose>
                            <c:when test="${sessionScope.role == 'admin'}">
                                <th>
                                    <fmt:message key="edit"/>
                                </th>
                                <th>
                                    <fmt:message key="report"/>
                                </th>
                                <th>
                                    <fmt:message key="delete"/>
                                </th>
                            </c:when>
                            <c:when test="${sessionScope.role == 'user'}">
                                <th>
                                    <fmt:message key="months"/>
                                </th>
                                <th>
                                    <fmt:message key="subscript"/>
                                </th>
                            </c:when>
                        </c:choose>
                    </tr>
                    <c:forEach items="${requestScope.publications}" var="publication">
                        <tr>
                            <td><c:out value="${publication.title}"/></td>
                            <td><c:out value="${publication.author}"/></td>
                            <td><c:out value="${publication.genre}"/></td>
                            <td><ex:formatCurrency money="${publication.price}" locale="${sessionScope.locale}"/></td>
                            <td><c:out value="${publication.description}"/></td>

                            <c:choose>
                                <c:when test="${sessionScope.role == 'admin'}">
                                    <td>

                                        <form method="POST" action="${pageContext.request.contextPath}/admin/editPublication">


                                            <input type="hidden" name="pubId" value="${publication.id}">
                                            <input type="hidden" name="command" value="/admin/catalog">
                                            <input type="hidden" name="query"
                                                   value="${pageContext.request.queryString}">

                                            <input type="submit" value="<fmt:message key="edit"/>">

                                        </form>

                                    </td>
                                    <td>
                                        <form method="POST" action="${pageContext.request.contextPath}/admin/showReport">

                                            <input type="hidden" name="pubId" value="${publication.id}">
                                            <input type="submit" value="<fmt:message key="report"/>">

                                        </form>
                                    </td>

                                    <td>
                                        <form method="POST"
                                              action="${pageContext.request.contextPath}/admin/deletePublication">


                                            <input type="hidden" name="pubId" value="${publication.id}">
                                            <input type="hidden" name="title" value="${publication.title}">
                                            <input type="hidden" name="command" value="/admin/catalog">
                                            <input type="hidden" name="query"
                                                   value="${pageContext.request.queryString}">
                                            <input type="submit" value="<fmt:message key="delete"/>">

                                        </form>
                                    </td>


                                </c:when>
                                <c:when test="${sessionScope.role == 'user'}">
                                    <form method="POST" action="${pageContext.request.contextPath}/user/subscript">
                                        <input type="hidden" name="pubId" value="${publication.id}">
                                        <input type="hidden" name="price" value="${publication.price}">
                                        <td>
                                            <input type="number" name="months" style="width: 80px"
                                                   placeholder="<fmt:message key="months"/> ">
                                        </td>

                                        <td>
                                            <input type="submit" style="width: 110px" value="<fmt:message key="subscribe"/>">

                                        </td>
                                    </form>

                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>


                <div class="col-xs-1" align="center">
                    <nav>
                        <ul class="pagination">
                            <c:if test="${paginationParameters.currentPage != 1}">

                                <li class="page-item"><a class="page-link"
                                                         href="/${sessionScope.role}/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${paginationParameters.numberOfPages}" var="i">

                                <c:choose>
                                    <c:when test="${paginationParameters.currentPage eq i}">
                                        <li class="page-item active"><a class="page-link">
                                                ${i} <span class="sr-only">(current)</span></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="/${sessionScope.role}/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${paginationParameters.currentPage lt paginationParameters.numberOfPages}">
                                <li class="page-item"><a class="page-link"
                                                         href="/${sessionScope.role}/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container-fluid mt-3 font-weight-bold">
                    <p class="text-center text-danger">
                            ${fail}
                    </p>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
    </div>

</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>




