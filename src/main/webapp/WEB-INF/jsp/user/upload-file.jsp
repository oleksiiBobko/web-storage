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
    <div class="row">
            <a href="<c:url value="/" />"><spring:message code="label.content"/></a>
            <form:form method="post" action="save" commandName="picture" enctype="multipart/form-data">
            <div class="col-lg-6">
                <!--form:errors path="*" cssClass="error"/-->
                <div class="form-group">
                    <label for="InputName">Enter name</label>
                    <div class="input-group">
                        <form:input type="text" class="form-control" path="owner" placeholder="author" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        <form:errors path="owner" cssClass="error"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="InputName">Enter description</label>
                    <div class="input-group">
                        <form:textarea type="text" class="form-control" path="description" placeholder="description" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        <form:errors path="description" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="InputName">Enter path</label>
                    <div class="input-group">
                        <form:input type="text" class="form-control" path="path" placeholder="path" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        <form:errors path="path" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputName">Enter path</label>
                    <div class="input-group">
                <input type="file" name="file" id="file" class="btn btn-info pull-left">
                </div>
                </div>
                <input type="submit" value="<spring:message code="label.add"/>" class="btn btn-info pull-left" />
            </div>
            </form:form>
    </div>
</div>
</body>
</html>