<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="panel-body">
        <form class="form-signin" name="login" method="post" action="<c:url value="/j_spring_security_check" />">
            <div class="form-group">
                <input id="email" placeholder="Nickname or email address" class="form-control input-sm" name="j_username" type="text">
            </div>
            <div class="form-group">
            <a href="/reset_password"><small>Forgot password?</small></a>
                <input class="form-control input-sm" placeholder="Password" name="j_password" id="inputPassword" type="password">
            </div>
            <div class="form-group">
                <input type="checkbox" value="remember-me"><small>Remember me</small>
            </div>
            <div class="form-group">
                <input class="btn btn-info btn-block" type="submit" value="<spring:message code="label.login" />" />
            </div>
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger" role="alert"><spring:message code="label.loginerror" />
                    : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
        </form>
    </div>
