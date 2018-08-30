<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Catalog</title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/userMenu.jsp"/>
<fmt:bundle basename="pagecontent" prefix="field.">
<form method="get" action="${pageContext.request.contextPath}/app/search">
    <fmt:message key="search"/>
    <br/>
    <fmt:message key="title"/>
    <br/>
    <input type="text" name="title">
    <br/>
    <fmt:message key="genre"/>
    <br/>
    <input type="text" name="genre">
    <br/>
    <fmt:message key="price"/>
    <br/>
    <input type="number" name="leftPriceBoundary">
    <br/>
    <input type="number" name="rightPriceBoundary">

    <input type="submit" value="<fmt:message key="search"/>"/>
    <br/>
        ${fail}
</form>
</fmt:bundle>
<br/>
<br/>

<fmt:bundle basename="pagecontent" prefix="field.">

<form action="${pageContext.request.contextPath}/app/catalog">

    <input type="hidden" name="currentPage" value="1">



    <label for="records">Select records per page:</label>

    <select class="form-control" id="records" name="recordsPerPage">
        <option value="5">5</option>
        <option value="10" selected>10</option>
        <option value="15">15</option>
    </select>



    <button type="submit">Submit</button>

</form>
<br/>


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

<table border="1" cellpadding="5">
    <caption><h2>Periodicals List</h2></caption>
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
            <td><fmt:formatNumber value="${publication.price}"  type="currency"/></td>
            <td><c:out value="${publication.description}"/></td>
            <c:if test="${sessionScope.role == 'user'}">
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/app/subscript">
                        <input type="hidden" name="pubId" value="${publication.id}">
                        <input type="hidden" name="price" value="${publication.price}">
                        <fmt:message key="from"/><br>
                        <input type="date" name="from" min="1"><br>
                        <fmt:message key="to"/><br>
                        <input type="number" name="months">
                        <br>
                        <input type="hidden" name="subId" value="${publication.id}">
                        <input type="submit" value="<fmt:message key="subscript"/>">
                    </form>
                </td>
            </c:if>

                <%--<td>
                    <a href="/delete?name=<c:out value='${image.name}' />">Delete</a>
                </td>
                <td>
                    <input type="checkbox" name="${image.name}" >
                </td>--%>
        </tr>
    </c:forEach>
</table>
</fmt:bundle>




