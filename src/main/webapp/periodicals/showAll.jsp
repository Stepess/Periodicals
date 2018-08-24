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
<jsp:include page="/WEB-INF/component/mainBody.jsp"/>
<fmt:bundle basename="pagecontent" prefix="field.">
    ${fail}
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
        </tr>
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
