<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<footer class="foot">
    <div class="container-fluid">
        <div class="container">
            <div class="rightsSignBlock">
                <span class="rightsSign">©2018 Periodicals<br></span>
                <fmt:bundle basename="pagecontent" prefix="label.">
                    <span class="rightsSign"><fmt:message key="designed"/><br></span>
                </fmt:bundle>
            </div>
        </div>
    </div>
</footer>


