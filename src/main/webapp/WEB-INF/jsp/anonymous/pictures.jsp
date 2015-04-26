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
	<div>
		<c:forEach items="${pictures}" var="picture">
			<div>
				<c:if test="${authorized}">
					<a href="<c:url value="/delete/${picture.id}" />"><spring:message code="label.delete" /></a>
				</c:if>
			</div>
<%-- 			<a href='<c:url value="${picture.path}"/>' class="highslide" onclick="return hs.expand(this)"> --%>
<%-- 				<img src="${picture.thumbnail}" alt="${picture.filename}" title="Click to enlarge" /> --%>
<!-- 			</a> -->
			<div class="highslide-caption">${picture.description}</div>
<!-- 					target="_blank" -->
					<a href='<c:url value="${picture.path}"/>' >${picture.filename}</a>
					<img src="${picture.thumbnail}" >
		</c:forEach>
	</div>
</c:if>