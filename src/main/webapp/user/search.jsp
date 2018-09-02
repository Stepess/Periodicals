<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Catalog</title>
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
<fmt:bundle basename="pagecontent" prefix="field.">
    <div class="container vertical-tb-offset-10">
        <form method="get" action="${pageContext.request.contextPath}/app/search">
            <div class="form-row">
                <div class="col">
                    <input name="title" type="text" class="form-control" placeholder="<fmt:message key="title"/>">
                </div>
                <div class="col">
                    <input name="genre" type="text" class="form-control" placeholder="<fmt:message key="genre"/>">
                </div>
                <div class="col">
                    <input name="leftPriceBoundary" type="number" class="form-control"
                           placeholder="<fmt:message key="price"/>">
                </div>
                <div class="col">
                    <input name="rightPriceBoundary" type="number" class="form-control"
                           placeholder="<fmt:message key="price"/>">
                </div>
                <div class="col">
                    <input class="btn btn-success" type="submit" value="<fmt:message key="search"/>"/>
                </div>

            </div>
            <div class="container">
                <p class="text-danger">
                        ${fail}
                </p>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/app/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}"
              onchange="submit()">
            <input type="hidden" name="currentPage" value="1">
            <label for="records">Select records per page:</label>
            <select class="form-control" id="records" name="recordsPerPage">
                <option velue="default" selected>default</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="15">15</option>
            </select>
        </form>
    </div>
    <div class="container">
        <table class="table">
            <h2>Periodicals List</h2>
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
                </th>
            </tr>
            <c:forEach items="${requestScope.publications}" var="publication">
                <tr>
                    <td><c:out value="${publication.title}"/></td>
                    <td><c:out value="${publication.author}"/></td>
                    <td><c:out value="${publication.genre}"/></td>
                    <td><fmt:formatNumber value="${publication.price}" type="currency"/></td>
                    <td><c:out value="${publication.description}"/></td>
                    <c:if test="${sessionScope.role == 'user'}">
                        <td>
                            <form method="POST" action="${pageContext.request.contextPath}/app/subscript">
                                <input type="hidden" name="pubId" value="${publication.id}">
                                <input type="hidden" name="price" value="${publication.price}">
                                <fmt:message key="to"/><br>
                                <input type="number" name="months">
                                <br>
                                <input type="submit" value="<fmt:message key="subscript"/>">
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>


        <div class="col-xs-1" align="center">
            <nav>
                <ul class="pagination">
                    <c:if test="${paginationParameters.currentPage != 1}">

                        <li class="page-item"><a class="page-link"
                                                 href="/app/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                                         href="/app/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${paginationParameters.currentPage lt paginationParameters.numberOfPages}">
                        <li class="page-item"><a class="page-link"
                                                 href="/app/search?title=${title}&genre=${genre}&leftPriceBoundary=${leftPriceBoundary}&rightPriceBoundary=${rightPriceBoundary}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>



