<%@ page language="java" contentType="text/html; charset=utf8"
        pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
        prefix="sec"%>


<c:if test="${not empty param.error}">
<font color="red"> <spring:message code="label.addError" /></font>
</c:if>
<c:if test="${!empty pictures}">
<c:forEach items="${pictures}" var="picture">
<h2>${picture.filename}</h2>
<div class="panel-group">
    <div class="panel panel-default">
<c:choose>
    <c:when test="${not empty picture.thumbnail}">
        <a href='<c:url value="${picture.path}"/>' class="highslide" onclick="return hs.expand(this)">
        <img src="${picture.thumbnail}" class="img-responsive img-rounded" alt="${picture.filename}" title="Click to enlarge" />
        </a>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
        <div class="panel-heading">
            <h3 class="panel-title pull-left">
                <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#collapse${picture.id}">Show more...</button>
            </h3>
            <c:if test="${authorized}">
            <a href="#" class="panel-title pull-right" data-href="<c:url value="/delete/${picture.id}" />"
                data-toggle="modal" data-target="#confirm-delete">
                <span class="glyphicon glyphicon-remove"></span>
            </a>
            </c:if>
            <div class="clearfix"></div>
        </div>
        <div class="panel-body">
        <div id="collapse${picture.id}" class="panel-collapse collapse borderless">
        <ul class="list-group">
            <li class="list-group-item">File: <a href='<c:url value="${picture.path}"/>' target="_blank">${picture.filename}</a></li>
            <li class="list-group-item">Original: <a href="#" >http://originallink.com/document.suffix</a></li>
            <li class="list-group-item">Owner: <a href="#" >${picture.owner}</a></li>
            <li class="list-group-item">Size: over 9000Gb</li>
        </ul>
        </div>
        </div>
        </div>
</div>
<div>
</div>
</c:forEach>

</c:if>
