<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
    <div class="panel-body">
        <form:form class="form-signin" name="send_reset_password_mail" method="post" modelAttribute="email"
            action="reset_password">
            <c:if test="${not empty param.error}">
                <font color="red"> <spring:message code="label.loginerror" />
                    : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </font>
            </c:if>
            <div class="form-group">
                <small>email address</small>
                <input id="email" placeholder="Email address" class="form-control input-sm" name="email" type="text">
            </div>
            <div class="form-group">
                <input class="btn btn-default btn-block" type="submit" value="reset" />
            </div>
        </form:form>
        <c:if test="${email_sent}">
            <div class="alert alert-success" role="alert">email sent</div>
        </c:if>
    </div>
</div>