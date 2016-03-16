<%@ page language="java" contentType="text/html; charset=utf8"
        pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
        prefix="sec"%>
<c:choose>
    <c:when test="${code eq 'ok'}">
        <div class="alert alert-success" role="alert">Account <b>${user}</b> has been activated successfully</div>
    </c:when>
    <c:when test="${code eq 'notActive'}">
        <div class="alert alert alert-danger" role="alert">Account <b>${user}</b> is not active</div>
    </c:when>
</c:choose>