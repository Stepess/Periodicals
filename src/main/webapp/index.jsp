<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/component/header.jsp"/>
<c:choose>
    <c:when test="admin">
        <jsp:include page="/WEB-INF/component/adminMenu.jsp"/>
    </c:when>
    <c:when test="user">
        <jsp:include page="/WEB-INF/component/userMenu.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/component/guestMenu.jsp"/>
    </c:otherwise>
</c:choose>

    <div class="container vertical-offset-100">
        <div class="row">
            <div class="col-lg-4 ">
                <div class=" margin well">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer dictum mauris varius maximus
                    interdum. Cras fringilla molestie dui eu interdum. Suspendisse maximus est ut risus iaculis, et
                    tempor metus luctus. Ut eget tempor libero. Nunc augue nibh, maximus ut molestie in, blandit dictum
                    ante. Nam ipsum magna, sodales nec diam in, varius eleifend metus. Pellentesque ut lacus sem. Donec
                    nec felis diam. Quisque at nisi interdum, ultricies metus in, rutrum ex. Duis iaculis erat vel
                    efficitur tempor. Integer quis mauris nisi. Morbi ut tempus tellus, at scelerisque lorem. Nulla
                    efficitur pharetra elit eu cursus. Nunc faucibus turpis nec ultricies congue. Etiam elit turpis,
                    tristique molestie sem eget, molestie pulvinar enim. Morbi rhoncus turpis eget consequat dictum.

                    Nulla non feugiat eros. Ut erat urna, sagittis eget rhoncus eu, luctus vel lacus. Fusce aliquam est
                    orci, nec vestibulum lectus vestibulum quis. Nulla facilisi. Curabitur sed metus nec lorem aliquet
                    ullamcorper. Ut lacus metus, accumsan ut fringilla a, lobortis a nisl. Cras efficitur laoreet sem,
                    sit amet fringilla magna tristique at. Quisque sodales ac metus in interdum. Cras et dapibus urna,
                    at faucibus sapien. Nunc consectetur commodo felis vel tempor. Fusce vitae volutpat sapien. Duis
                    vulputate ultrices consequat. Quisque nulla massa, aliquet ac posuere et, finibus et est. Morbi
                    vulputate orci in nibh imperdiet sodales. Mauris a purus eget turpis aliquet maximus.

                </div>
            </div>
            <div class="col-lg-4 ">
                <div class=" margin well">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer dictum mauris varius maximus
                    interdum. Cras fringilla molestie dui eu interdum. Suspendisse maximus est ut risus iaculis, et
                    tempor metus luctus. Ut eget tempor libero. Nunc augue nibh, maximus ut molestie in, blandit dictum
                    ante. Nam ipsum magna, sodales nec diam in, varius eleifend metus. Pellentesque ut lacus sem. Donec
                    nec felis diam. Quisque at nisi interdum, ultricies metus in, rutrum ex. Duis iaculis erat vel
                    efficitur tempor. Integer quis mauris nisi. Morbi ut tempus tellus, at scelerisque lorem. Nulla
                    efficitur pharetra elit eu cursus. Nunc faucibus turpis nec ultricies congue. Etiam elit turpis,
                    tristique molestie sem eget, molestie pulvinar enim. Morbi rhoncus turpis eget consequat dictum.

                    Nulla non feugiat eros. Ut erat urna, sagittis eget rhoncus eu, luctus vel lacus. Fusce aliquam est
                    orci, nec vestibulum lectus vestibulum quis. Nulla facilisi. Curabitur sed metus nec lorem aliquet
                    ullamcorper. Ut lacus metus, accumsan ut fringilla a, lobortis a nisl. Cras efficitur laoreet sem,
                    sit amet fringilla magna tristique at. Quisque sodales ac metus in interdum. Cras et dapibus urna,
                    at faucibus sapien. Nunc consectetur commodo felis vel tempor. Fusce vitae volutpat sapien. Duis
                    vulputate ultrices consequat. Quisque nulla massa, aliquet ac posuere et, finibus et est. Morbi
                    vulputate orci in nibh imperdiet sodales. Mauris a purus eget turpis aliquet maximus.

                </div>
            </div>
            <div class="col-lg-4 ">
                <div class=" margin well">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer dictum mauris varius maximus
                    interdum. Cras fringilla molestie dui eu interdum. Suspendisse maximus est ut risus iaculis, et
                    tempor metus luctus. Ut eget tempor libero. Nunc augue nibh, maximus ut molestie in, blandit dictum
                    ante. Nam ipsum magna, sodales nec diam in, varius eleifend metus. Pellentesque ut lacus sem. Donec
                    nec felis diam. Quisque at nisi interdum, ultricies metus in, rutrum ex. Duis iaculis erat vel
                    efficitur tempor. Integer quis mauris nisi. Morbi ut tempus tellus, at scelerisque lorem. Nulla
                    efficitur pharetra elit eu cursus. Nunc faucibus turpis nec ultricies congue. Etiam elit turpis,
                    tristique molestie sem eget, molestie pulvinar enim. Morbi rhoncus turpis eget consequat dictum.

                    Nulla non feugiat eros. Ut erat urna, sagittis eget rhoncus eu, luctus vel lacus. Fusce aliquam est
                    orci, nec vestibulum lectus vestibulum quis. Nulla facilisi. Curabitur sed metus nec lorem aliquet
                    ullamcorper. Ut lacus metus, accumsan ut fringilla a, lobortis a nisl. Cras efficitur laoreet sem,
                    sit amet fringilla magna tristique at. Quisque sodales ac metus in interdum. Cras et dapibus urna,
                    at faucibus sapien. Nunc consectetur commodo felis vel tempor. Fusce vitae volutpat sapien. Duis
                    vulputate ultrices consequat. Quisque nulla massa, aliquet ac posuere et, finibus et est. Morbi
                    vulputate orci in nibh imperdiet sodales. Mauris a purus eget turpis aliquet maximus.

                </div>
            </div>
        </div>
    </div>

<jsp:include page="/WEB-INF/component/footer.jsp"/>
</body>

</html>
