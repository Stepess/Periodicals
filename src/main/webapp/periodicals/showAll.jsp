<%--
  Created by IntelliJ IDEA.
  User: Senpai
  Date: 22.08.2018
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
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
<c:if test="${sessionScope.role == 'user'}">
    <jsp:include page="/WEB-INF/component/userMenu.jsp"/>
</c:if>

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

        <ul class="pagination">
            <c:if test="${paginationParameters.currentPage != 1}">
                <%--<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>--%>

                <%--<my:setParam name='recordsPerPage' value='${paginationParameters.recordsPerPage}' />
                <my:setParam name='currentPage' value='${paginationParameters.currentPage-1}' />
                <li class="page-item"><a class="page-link"
                                         href="/app/catalog?${pageContext.request.queryString}">Previous</a>
                </li>--%>
                <li class="page-item"><a class="page-link"
                                         href="/app/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${paginationParameters.numberOfPages}" var="i">

                <%--     ${pageContext.request.requestURL}
                     ${pageContext.request.queryString}--%>
                <c:choose>
                    <c:when test="${paginationParameters.currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <%--<my:setParam name='recordsPerPage' value='${paginationParameters.recordsPerPage}' />
                        <my:setParam name='currentPage' value='${i}' />
                        <li class="page-item"><a class="page-link"
                                                 href="/app/catalog?${pageContext.request.queryString}">${i}</a>
                        </li>--%>
                        <li class="page-item"><a class="page-link"
                                                 href="/app/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${paginationParameters.currentPage lt paginationParameters.numberOfPages}">
                <%--<my:setParam name='recordsPerPage' value='${paginationParameters.recordsPerPage}' />
                <my:setParam name='currentPage' value='${paginationParameters.currentPage+1}' />
                <li class="page-item"><a class="page-link"
                                         href="/app/catalog?${pageContext.request.queryString}">Next</a>
                </li>--%>

                <li class="page-item"><a class="page-link"
                                         href="/app/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>



        <c:forEach items="${requestScope.publications}" var="publication">
            <tr>
                <td><c:out value="${publication.title}"/></td>
                <td><c:out value="${publication.author}"/></td>
                <td><c:out value="${publication.genre}"/></td>
                <td><c:out value="${publication.price}"/></td>
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
</body>
</html>
