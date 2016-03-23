<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ctxt" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${ctxt}">Storage</a>
    </div>
    
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <c:if test="${authorized}">
      <form:form class="navbar-form navbar-left" role="search" method="post" action="grab" commandName="url" enctype="multipart/form-data" id="grub">
        <div class="form-group">
        <form:input type="text" placeholder="URL" class="form-control" path="URL" />
        </div>
        <button type="submit" class="btn btn-default">Save</button>
      </form:form>
    </c:if>
      <ul class="nav navbar-nav navbar-right">
        <c:if test="${not authorized}">
            <li><button type="button" class="btn btn-info navbar-btn signup-button" id="signup_button">Sign up</button></li>
            <script>
                $('#signup_button').click(function() {
                    window.location = "/registration";
                });
            </script>
            <li><button type="button" class="btn btn-default navbar-btn signin-button" id="signin_button" >Sign in</button></li>
            <script>
                $('#signin_button').click(function() {
                    window.location = "/login";
                });
            </script>
        </c:if>
        <c:if test="${authorized}">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">more<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">u:<sec:authentication property="principal.username" /></a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Create storage</a></li>
            <li><a href="<c:url value="/add" />">Upload</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="<c:url value="/logout" />">Sign out</a></li>
          </ul>
        </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
