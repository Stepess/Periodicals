
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="pagecontent" prefix="label.">
<head>
    <title><fmt:message key="add.publication"/> </title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/adminMenu.jsp"/>

<div class="container center-block">
    <div class="row vertical-offset-100 justify-content-center">
        <div class="col-md-4 col-md-offset-4 well">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign in</h3>
                </div>
                <div class="panel-body">
                    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/addPublication">
                        <fieldset>
                            <div class="form-group">
                                <label for="title_en"><fmt:message key="title_en"/></label>
                                <input class="form-control" value="${param.titleEn}" name="title_en" id="title_en" type="text" required>
                                <p class="text-danger">
                                        ${wrongtitle_en}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="title_ua"><fmt:message key="title_ua"/></label>
                                <input class="form-control" id="title_ua" value="${param.titleUa}" name="title_ua" type="text" required>
                                <p class="text-danger">
                                        ${wrongtitle_ua}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="author"><fmt:message key="author"/></label>
                                <input class="form-control" value="${param.author}" name="author" id="author" type="text" required>
                                <p class="text-danger">
                                        ${wrongauthor}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="genre_en"><fmt:message key="genre_en"/></label>
                                <input class="form-control" value="${param.genreEn}" name="genre_en" id="genre_en" type="text" required>
                                <p class="text-danger">
                                        ${wronggenre_en}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="genre_ua"><fmt:message key="genre_ua"/></label>
                                <input class="form-control" value="${param.genreUa}" name="genre_ua" id="genre_ua" type="text" required>
                                <p class="text-danger">
                                        ${wronggenre_ua}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="price"><fmt:message key="price"/></label>
                                <input class="form-control" value="${param.price}" name="price" id="price" type="number" step=".01" required>
                                <p class="text-danger">
                                        ${wrongprice}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="description_en"><fmt:message key="description_en"/></label>
                                <textarea class="form-control" class="form-control" rows="5" name="description_en" id="description_en" required>${param.descriptionEn}</textarea>
                                <p class="text-danger">
                                        ${wrongdescription_en}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="description_ua"><fmt:message key="description_ua"/></label>
                                <textarea class="form-control" class="form-control" rows="5" name="description_ua" id="description_ua" required>${param.descriptionUa}</textarea>
                                <p class="text-danger">
                                        ${wrongdescription_ua}
                                </p>
                            </div>
                            <input type="submit" value="<fmt:message key="add.publication"/>"/>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

        <%--<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/addPublication">
            <fmt:message key="title_en"/><br/>
            <input type="text" name="title_en" value="${param.title_en}"/>
            <br/>
            ${wrongtitle_en}
            <br/>
            <fmt:message key="title_ua"/><br/>
            <input type="text" name="title_ua" value="${param.title_ua}"/>
            <br/>
                ${wrongtitle_ua}
            <br/>
            <fmt:message key="author"/><br/>
            <input type="text" name="author" value="${param.author}"/>
            <br/>
                ${wrongauthor}
            <br/>
            <fmt:message key="genre_en"/><br/>
            <input type="text" name="genre_en" value="${param.genre_en}"/>
            <br/>
                ${wronggenre_en}
            <br/>
            <fmt:message key="genre_ua"/><br/>
            <input type="text" name="genre_ua" value="${param.genre_ua}"/>
            <br/>
                ${wronggenre_ua}
            <br/>
            <fmt:message key="price"/><br/>
            <input type="number" step=".01" name="price" value="${param.price}"/>
            <br/>
                ${wrongprice}
            <br/>
            <fmt:message key="description_en"/><br/>
            <input type="text" name="description_en" value="${param.description_en}"/>
            <br/>
                ${wrongdescription_en}
            <br/>
            <fmt:message key="description_ua"/><br/>
            <input type="text" name="description_ua" value="${param.description_ua}"/>
            <br/>
                ${wrongdescription_ua}
            <br/>



            <br/>

            <input type="submit" value="<fmt:message key="add.publication"/>"/>
        </form>--%>



</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>
