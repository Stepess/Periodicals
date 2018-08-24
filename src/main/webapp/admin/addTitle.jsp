<%--
  Created by IntelliJ IDEA.
  User: Senpai
  Date: 24.08.2018
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:bundle basename="pagecontent" prefix="label.">


        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/app/login">
            <fmt:message key="title_en"/><br/>
            <input type="text" name="title_en" value=""/>
            <br/>
            <fmt:message key="title_ua"/><br/>
            <input type="text" name="title_ua" value=""/>
            <br/>
            <fmt:message key="author"/><br/>
            <input type="text" name="author" value=""/>
            <br/>
            <fmt:message key="genre_en"/><br/>
            <input type="text" name="genre_en" value=""/>
            <br/>
            <fmt:message key="genre_ua"/><br/>
            <input type="text" name="genre_ua" value=""/>
            <br/>

                ${errorLoginPassMessage}
            <br/>
            <input type="submit" value="Log in"/>
        </form>



</fmt:bundle>
</body>
</html>
