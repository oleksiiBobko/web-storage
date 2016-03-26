<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
    <sec:authorize access="hasRole('ROLE_USER')">
      <form class="navbar-form navbar-left" method="post" action="grab" 
      enctype="multipart/form-data" id="url-form">
        <div class="form-group">
        <input type="text" id="submit-url" placeholder="URL" name="url" class="form-control" />
        <input type="hidden" id="option" name="option"/>
        </div>
        <button type="button" id="submit-btn" class="btn btn-default" data-toggle="modal" data-target="#confirm-submit">
            Save
        </button>
      </form>
    </sec:authorize>
      <ul class="nav navbar-nav navbar-right">
        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
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
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
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
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>
<div class="modal fade" id="confirm-submit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4 class="modal-title" id="myModalLabel">Actions</h4>
      </div>
      <div class="modal-body" id="select-action">
        <p><strong>URL:&nbsp</strong><span id="url"></span></p>
        <p><label class="radio-inline">
          <input name="radioName" id="inlineRadio1" value="1" checked="checked" type="radio"> Save link as file
        </label></p>
        <p><label class="radio-inline">
          <input name="radioName" id="inlineRadio2" value="2" type="radio"> Save link
        </label></p>
        <p><label class="radio-inline">
          <input name="radioName" id="inlineRadio3" value="3" type="radio"> Extract pictures
        </label></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a href="#" id="submit" class="btn btn-success success">Apply</a>
      </div>
    </div>
  </div>
</div>
<script>
$(document).ready(function() {
$('#submit-btn').click(function() {
     $('#url').text($('#submit-url').val());
});

$('#submit').click(function(){
    $("#option").val($('input[name=radioName]:checked', '#select-action').val());
    $('#url-form').submit();
});
});
</script>