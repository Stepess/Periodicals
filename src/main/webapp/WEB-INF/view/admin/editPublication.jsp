<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="pagecontent" prefix="label.">
<head>
    <title>
        <fmt:bundle basename="pagecontent" prefix="title.">
            <fmt:message key="edit"/>
        </fmt:bundle>
    </title>
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<jsp:include page="/WEB-INF/component/adminMenu.jsp"/>


<div class="container center-block">
    <div class="row vertical-offset-100 justify-content-center">
        <div class="col-md-4 col-md-offset-4 well">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="edit.publication"/></h3>
                </div>
                <div class="panel-body">
                    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/admin/editPublication">
                        <fieldset>
                            <div class="form-group">
                                <label for="title_en"><fmt:message key="title_en"/></label>
                                <input class="form-control" value="${publication.titleEn}" name="title_en" id="title_en" type="text" required>
                                <p class="text-danger">
                                        ${wrongtitle_en}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="title_ua"><fmt:message key="title_ua"/></label>
                                <input class="form-control" id="title_ua" value="${publication.titleUa}" name="title_ua" type="text" required>
                                <p class="text-danger">
                                        ${wrongtitle_ua}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="author"><fmt:message key="author"/></label>
                                <input class="form-control" value="${publication.author}" name="author" id="author" type="text" required>
                                <p class="text-danger">
                                        ${wrongauthor}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="genre_en"><fmt:message key="genre_en"/></label>
                                <input class="form-control" value="${publication.genreEn}" name="genre_en" id="genre_en" type="text" required>
                                <p class="text-danger">
                                        ${wronggenre_en}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="genre_ua"><fmt:message key="genre_ua"/></label>
                                <input class="form-control" value="${publication.genreUa}" name="genre_ua" id="genre_ua" type="text" required>
                                <p class="text-danger">
                                        ${wronggenre_ua}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="price"><fmt:message key="price"/></label>
                                <input class="form-control" value="${publication.price}" name="price" id="price" type="number" step=".01" required>
                                <p class="text-danger">
                                        ${wrongprice}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="description_en"><fmt:message key="description_en"/></label>
                                <textarea class="form-control" class="form-control" rows="5" name="description_en" id="description_en" required>${publication.descriptionEn}</textarea>
                                <p class="text-danger">
                                        ${wrongdescription_en}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="description_ua"><fmt:message key="description_ua"/></label>
                                <textarea class="form-control" class="form-control" rows="5" name="description_ua" id="description_ua" required>${publication.descriptionUa}</textarea>
                                <p class="text-danger">
                                        ${wrongdescription_ua}
                                </p>
                            </div>
                            <input type="hidden" name="flag" value="edit">
                            <input type="hidden" name="pubId" value="${param.pubId}">
                            <input type="hidden" name="command" value="${param.command}">
                            <input type="hidden" name="query" value="${param.query}">
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="<fmt:message key="edit"/>"/>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>
</html>