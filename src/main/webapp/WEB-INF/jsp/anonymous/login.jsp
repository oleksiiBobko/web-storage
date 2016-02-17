<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="author" content="oleksii.bobko">
<title>Login</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="resources/css/signin.css" />
</head>
<body>
<div class="container">
<form class="form-signin" name="login" method="post" action="<c:url value="/j_spring_security_check" />">
<c:if test="${not empty param.error}">
<font color="red"> <spring:message code="label.loginerror" /> : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</font>
</c:if>				
<input value="false" name="returning" id="returning" type="hidden">
<label class="sr-only" for="email"> <spring:message code="label.login" /></label>
<input id="email" placeholder="Email address" name="j_username" type="text" class="form-control">
<label class="sr-only" for="password">Password</label>
<input class="form-control"  placeholder="Password" name="j_password" id="inputPassword" type="password">
<a href="#">Forgot password?</a>
<span>|</span>
<a href="<c:url value="/registration" />"><spring:message code="label.registration" /></a>
<span>|</span>
<a href="<c:url value="/content" />"><spring:message code="label.content" /></a>
<div class="checkbox">
<label><input type="checkbox" value="remember-me">Remember me</label>
</div>
<input class="btn btn-lg btn-primary btn-block" type="submit" value="<spring:message code="label.login" />" />
</form>
</div>
</body>
</html>
