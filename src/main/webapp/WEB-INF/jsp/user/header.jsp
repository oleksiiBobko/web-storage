<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            <!--li><a href="<c:url value="#"/>">About Us</a></li>
            <li><a href="<c:url value="#" />">Messages</a></li>
            <li><a href="<c:url value="#" />">Audio</a></li-->
            <c:if test="${not authorized}">
            <li><a href="<c:url value="/login" />">Login</a></li>
            </c:if>
            <c:if test="${authorized}">
            <li>
                <form:form method="post" action="grab" commandName="url" enctype="multipart/form-data" id="grub" class="navbar-form navbar-right">
                <form:input type="text" placeholder="Search..." class="form-control" path="URL" />
                <input type="submit" class="btn btn-success" value="<spring:message code="label.grab" />" />
                </form:form>
            </li>
<li><a href="<c:url value="/add" />">Upload</a></li>

<li><a href="<c:url value="/logout" />"><spring:message code="label.logout" /></a></li>
<li><span>You logined as:<b><sec:authentication property="principal.username" /></b></span></li>

            </li>
            </c:if>
            </ul>
            </div>
        </div>
    </nav>
