<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
<link href="resources/style/home2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="resources/style/highslide.css" />
<script type="text/javascript" src="resources/js/script.js"></script>
<script type="text/javascript"
	src="resources/js/highslide-with-gallery.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
	hs.graphicsDir = '${pageContext.servletContext.contextPath}/resources/images/';
	hs.align = 'center';
	hs.transitions = [ 'expand', 'crossfade' ];
	hs.outlineType = 'glossy-dark';
	hs.wrapperClassName = 'dark';
	hs.fadeInOut = true;
	//hs.dimmingOpacity = 0.75;

	// Add the controlbar
	if (hs.addSlideshow)
		hs.addSlideshow({
			//slideshowGroup: 'group1',
			interval : 5000,
			repeat : false,
			useControls : true,
			fixedControls : 'fit',
			overlayOptions : {
				opacity : .6,
				position : 'bottom center',
				hideOnMouseOut : true
			}
		});
</script>

<style>
#header-wrap {
	background: #eeeeff;
	position: fixed;
	height: 50px;
	width: 100%;
	top: 0;
	z-index: 1;
}

#container {
	margin-top: 50px;
}
</style>

<title>Pictures</title>
</head>
<body>
		<div id="header-wrap">
			<c:if test="${not empty param.error}">
				<font color="red"> <spring:message code="label.addError" /></font>
			</c:if>

			</br>

			<ul id="nav">
				<li><a href="#">About Us</a></li>

				
				<li><a href="<c:url value="/login" />"><spring:message code="label.login" /></a></li>
								<li><a href="<c:url value="#" />">Messages</a></li>
				<li><a href="<c:url value="#" />">Audio</a></li>
				<li><a href="<c:url value="/add" />"><spring:message code="label.add" /></a></li>
				
				<li><a href="<c:url value="/registration" />"><spring:message code="label.registration" /></a></li>				 
				<li><a href="<c:url value="/logout" />"><spring:message code="label.logout" /></a></li>

				<li><c:if test="${authorized}">
						<form:form method="post" action="grab.html" commandName="url"
							enctype="multipart/form-data">
							<form:input path="URL" />

							<input type="submit" value="<spring:message code="label.grab" />" />
						</form:form>
					</c:if></li>
			</ul>
		</div>
		<div id="container">
			<div align="center">
				<div class="pagination">
					<a class="page" href="<c:url value="/goto/0" />"><spring:message
							code="label.begin" /></a> <a class="page"
						href="<c:url value="/prevPage" />"><spring:message
							code="label.prev" /></a>
					<c:if test="${!empty pages}">
						<c:forEach items="${pages}" var="depositPage">
							<c:choose>
								<c:when test="${depositPage.active}">
									<span class="page active">${depositPage.index}</span>
								</c:when>
								<c:otherwise>
									<a class="page"
										href="<c:url value="/goto/${depositPage.index}" />">${depositPage.index}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<a class="page" href="<c:url value="/nextPage" />"><spring:message
								code="label.next" /></a>
					</c:if>
				</div>
			</div>

			<c:if test="${!empty pictures}">
				<div align="center" class="highslide-gallery">
					<c:forEach items="${pictures}" var="picture">
						<div>
							<div>
								<c:if test="${authorized}">
									<a href="<c:url value="/delete/${picture.id}" />"> <spring:message
											code="label.delete" />
									</a>
								</c:if>
							</div>

							<a href='<c:url value="image/${picture.id}"/>' class="highslide"
								onclick="return hs.expand(this)"> <img src="${pageContext.servletContext.contextPath}/thumbimage/${picture.id}"
								alt="${picture.filename}" title="Click to enlarge" />
							</a>
							<div class="highslide-caption">${picture.description}</div>
							<br>
						</div>
						<br>
					</c:forEach>
				</div>
			</c:if>

			<div class="pagination">
				<a class="page" href="<c:url value="/goto/0" />"><spring:message
						code="label.begin" /></a> <a class="page"
					href="<c:url value="/prevPage" />"><spring:message
						code="label.prev" /></a>
				<c:if test="${!empty pages}">
					<c:forEach items="${pages}" var="depositPage">
						<c:choose>
							<c:when test="${depositPage.active}">
								<span class="page active">${depositPage.index}</span>
							</c:when>
							<c:otherwise>
								<a class="page"
									href="<c:url value="/goto/${depositPage.index}" />">${depositPage.index}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<a class="page" href="<c:url value="/nextPage" />"><spring:message
							code="label.next" /></a>
				</c:if>
			</div>
		</div>
</body>
</html>