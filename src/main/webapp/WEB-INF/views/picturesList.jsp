<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pictures</title>
</head>
<body>
	<c:if test="${not empty param.error}">
		<font color="red"> <spring:message code="label.addError" /></font>
	</c:if>

	</br>
	<hr>

	<div align="left">
		<a href="<c:url value="/login" />"><spring:message
				code="label.login" /></a>| <a href="<c:url value="/logout" />"><spring:message
				code="label.logout" /></a>| <a href="<c:url value="/registration" />"><spring:message
				code="label.registration" /></a>| <a href="<c:url value="/add" />"><spring:message
				code="label.add" /></a>

	</div>
	<div>
		<ul id="pagination-clean">
		    <li><a href="<c:url value="/goto/0" />"><spring:message
                code="label.begin" /></a></li>
			<li class="previous"><a href="<c:url value="/prevPage" />"><spring:message
						code="label.prev" /></a></li>
			<c:if test="${!empty pages}">
				<c:forEach items="${pages}" var="albumPage">
				<c:choose>
				    <c:when test="${albumPage.active}">
					<li class="active">${albumPage.index}</a></li>
					</c:when>
					<c:otherwise>
					<li><a href="<c:url value="/goto/${albumPage.index}" />">${albumPage.index}</a></li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
				<li class="next"><a href="<c:url value="/nextPage" />"><spring:message
							code="label.next" /></a></li>
			</c:if>
		</ul>
	</div>
	<div align="right">
		<form:form method="post" action="grab.html" commandName="url"
			enctype="multipart/form-data">
			<form:input path="URL" />
			<input type="submit" value="<spring:message code="label.grab" />" />
		</form:form>
	</div>
	<c:if test="${!empty pictures}">
		<div align="center">
			<c:forEach items="${pictures}" var="picture">
				<div>
					<div>
						<img
							src="${pageContext.servletContext.contextPath}/image/${picture.id}"
							alt="${picture.filename}">
					</div>
					<br>
					<div>${picture.owner}
						| ${picture.filename} | ${picture.description} | <a
							href="<c:url value="/delete/${picture.id}" />"> <img
							src="${pageContext.servletContext.contextPath}/resources/images/icon-remove.gif"
							alt="remove" width="30" height="30">
						</a>
					</div>
				</div>
				<br>
				<hr>
			</c:forEach>
		</div>
	</c:if>
	<div>
		<ul id="pagination-clean">
			<li class="previous"><a href="<c:url value="/prevPage" />"><spring:message
						code="label.prev" /></a></li>
			<c:if test="${!empty pages}">
				<c:forEach items="${pages}" var="albumPage">
					               <c:choose>
                    <c:when test="${albumPage.active}">
                    <li class="active">${albumPage.index}</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="<c:url value="/goto/${albumPage.index}" />">${albumPage.index}</a></li>
                    </c:otherwise>
                </c:choose>
				</c:forEach>
				<li class="next"><a href="<c:url value="/nextPage" />"><spring:message
							code="label.next" /></a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>