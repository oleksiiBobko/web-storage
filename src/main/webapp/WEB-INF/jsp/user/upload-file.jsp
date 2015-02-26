<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Album</title>
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container">
            <a href="<c:url value="/pictures" />"><spring:message code="label.album"/></a>
            <form:form method="post" action="save.html" commandName="picture" enctype="multipart/form-data">
                <!--form:errors path="*" cssClass="error"/-->
                <form:label path="owner"><spring:message code="label.author"/></form:label>
                <form:input path="owner" class="inputtext"/>
                <form:label path="description"><spring:message code="label.description"/></form:label>
                <form:textarea path="description" class="inputtext"/>
                <form:label path="path"><spring:message code="label.picture"/></form:label>
                <input type="file" name="file" id="file" >
                <input type="submit" value="<spring:message code="label.add" />" class="public-button"/>
            </form:form>
    </div>
</body>
</html>