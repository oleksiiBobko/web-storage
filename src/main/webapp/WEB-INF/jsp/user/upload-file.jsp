<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="panel-body">
<form:form method="post" action="save" commandName="picture"
    enctype="multipart/form-data">
    <div class="form-group">
        <form:textarea rows="10" type="text" class="form-control input-sm" path="description"
            placeholder="description" />
        <form:errors path="description" cssClass="error" />
    </div>
    <div class="form-group">
        <input type="file" name="file" id="file" />
    </div>
    <div class="form-group">
        <input type="submit" value="<spring:message code="label.add"/>"
            class="btn btn-info btn-block" />
    </div>
</form:form>
</div></div>
