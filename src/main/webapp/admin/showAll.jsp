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
<jsp:include page="/WEB-INF/component/adminMenu.jsp"/>
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
<fmt:bundle basename="pagecontent" prefix="label.">
    <table border="1" cellpadding="5">
        <caption><h2>Periodicals List</h2></caption>
        <tr>
            <th>
                <fmt:message key="title_en"/>
            </th>
            <th>
                <fmt:message key="title_ua"/>
            </th>
            <th>
                <fmt:message key="author"/>
            </th>
            <th>
                <fmt:message key="genre_en"/>
            </th>
            <th>
                <fmt:message key="genre_ua"/>
            </th>
            <th>
                <fmt:message key="price"/>
            </th>
            <th>
                <fmt:message key="description_en"/>
            </th>
            <th>
                <fmt:message key="description_ua"/>
            </th>


            </th>
        </tr>
        <c:forEach items="${requestScope.publications}" var="publication">
            <tr>
                <td><c:out value="${publication.titleEn}"/></td>
                <td><c:out value="${publication.titleUa}"/></td>
                <td><c:out value="${publication.author}"/></td>
                <td><c:out value="${publication.genreEn}"/></td>
                <td><c:out value="${publication.genreUa}"/></td>
                <td><c:out value="${publication.price}"/></td>
                <td><c:out value="${publication.descriptionEn}"/></td>
                <td><c:out value="${publication.descriptionUa}"/></td>


                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/admin/editPublication.jsp">
                            <input type="hidden" name="pubId" value="${publication.id}">
                            <input type="hidden" name="title_en" value="${publication.titleEn}">
                            <input type="hidden" name="title_ua" value="${publication.titleUa}">
                            <input type="hidden" name="author" value="${publication.author}">
                            <input type="hidden" name="genre_en" value="${publication.genreEn}">
                            <input type="hidden" name="genre_ua" value="${publication.genreUa}">
                            <input type="hidden" name="price" value="${publication.price}">

                            <input type="hidden" name="description_en" value="${publication.descriptionEn}">
                            <input type="hidden" name="description_ua" value="${publication.descriptionUa}">

                            <input type="submit" value="<fmt:message key="edit"/>">
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/app/showReport">
                            <input type="hidden" name="pubId" value="${publication.id}">
                            <input type="submit" value="<fmt:message key="report"/>">
                        </form>
                    </td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/app/delete">
                        <input type="hidden" name="pubId" value="${publication.id}">
                        <input type="submit" value="<fmt:message key="delete"/>">
                    </form>
                </td>


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
