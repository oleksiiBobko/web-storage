<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
    <div class="panel-body">
        <form class="form-signin" name="login" method="post"
            action="<c:url value="/j_spring_security_check" />">
            <c:if test="${not empty param.error}">
                <font color="red"> <spring:message code="label.loginerror" />
                    : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </font>
            </c:if>
            <div class="form-group">
                <small>Username or email address</small>
                <input id="email" placeholder="Email address" class="form-control input-sm" name="j_username" type="text">
            </div>
            <div class="form-group">
            <small>Password</small>
            <a href="/reset_password"><small>Forgot password?</small></a>
                <input class="form-control input-sm" placeholder="Password" name="j_password" id="inputPassword" type="password">
            </div>
            <div class="form-group">
                <input type="checkbox" value="remember-me"><small>Remember me</small>
            </div>
            <div class="form-group">
                <input class="btn btn-default btn-block" type="submit" value="<spring:message code="label.login" />" />
            </div>
        </form>
    </div>
</div>