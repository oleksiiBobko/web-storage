<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
<div class="panel-group">
<ul class="pagination">
    <c:if test="${fn:length(pages) gt 0}">
    <li><a href="<c:url value="/goto/0" />"><spring:message code="label.begin" /></a></li>
    <li><a class="page" href="<c:url value="/prevPage" />"> <spring:message code="label.prev" /></a></li>
        <c:forEach items="${pages}" var="depositPage">
            <c:choose>
                <c:when test="${depositPage.active}">
                    <li class="active"><a href="#">${depositPage.index}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a class="page" href="<c:url value="/goto/${depositPage.index}" />">${depositPage.index}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li><a class="page" href="<c:url value="/nextPage" />"><spring:message code="label.next" /></a></li>
    </c:if>
</ul>
</div>
</div>

