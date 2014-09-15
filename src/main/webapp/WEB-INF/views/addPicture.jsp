<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Album</title>
    <link href="resources/style/home2.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        .ll-content-notification * {
            letter-spacing: normal !important;
            margin: 0 !important;
            padding: 0 !important;
            background: none !important;
            border: 0 !important;
            float: none !important;
            text-align: left !important;
            text-decoration: none !important;
            font: normal 15px 'Lucida Grande', 'Lucida Sans Unicode', Lucida, Arial, Helvetica, sans-serif !important;
        }

        .ll-content-notification-header-pic img {
            border: 0 !important;
            padding: 0 !important;
            margin: 0 !important;
            line-height: 1px !important;
        }

    </style>


</head>
<body  class="small login">

<div id="header">
    <div class="wrapper">
        <h1><a href="https://www.survs.com/">Survs – Collaborative Online Survey Tool, Web Survey Software</a></h1>
    </div>
</div>

<div id="container">
    <div id="content">
        <div class="wrapper">
            <a href="<c:url value="/pictures" />">
            <spring:message code="label.album"/>
            </a>
            <form:form method="post" action="save.html" commandName="picture" enctype="multipart/form-data">
                <!--form:errors path="*" cssClass="error"/-->
                <table>
                    <tbody>
                    <tr>
                        <td><form:label path="owner"><spring:message code="label.author"/></form:label></td>
                        <td><form:input path="owner" class="inputtext"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="description"><spring:message code="label.description"/></form:label></td>
                        <td><form:textarea path="description" class="inputtext"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="path"><spring:message code="label.picture"/></form:label></td>
                        <td><input type="file" name="file" id="file" ></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="<spring:message code="label.add" />" class="public-button"/></td>
                    </tr>
                    </tbody>
                </table>
            </form:form>
    </div>
</div>
</div>
</body>
</html>