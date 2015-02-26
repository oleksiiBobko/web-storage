<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="resources/css/sticky-footer-navbar.css" rel="stylesheet">
<link href="resources/style/highslide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/script.js"></script>
<script type="text/javascript" src="resources/js/highslide-with-gallery.js"></script>

<meta http-equiv="Content-Type" content="text/html;">

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

<title>Pictures</title>
<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-53f3b27b3fa051c3"></script>
</head>
<body>
        <nav class="navbar navbar-default navbar-fixed-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Album</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
            <li><a href="<c:url value="#"/>">About Us</a></li>
            <li><a href="<c:url value="#" />">Messages</a></li>
            <li><a href="<c:url value="#" />">Audio</a></li>
            <li><a href="<c:url value="/add" />"><spring:message code="label.add" /></a></li>
            <li><a href="<c:url value="/logout" />"><spring:message code="label.logout" /></a></li>
            </ul>
            <c:if test="${authorized}">
            <form:form method="post" action="grab" commandName="url" enctype="multipart/form-data" id="grub" class="navbar-form navbar-right">
            <form:input type="text" placeholder="Search..." class="form-control" path="URL" />
            <input type="submit" class="btn btn-success" value="<spring:message code="label.grab" />" />
            </form:form>  
            </c:if>        
            </div>
          </div>
        </nav>
      
	<div class="container">
      <div class="page-header">
				<c:if test="${not empty param.error}">
					<font color="red"> <spring:message code="label.addError" /></font>
				</c:if>

                <ul class="pagination">
                    <li>
                    <a href="<c:url value="/goto/0" />"><spring:message code="label.begin" /></a>
                    </li>
                    <li> 
                    <a class="page" href="<c:url value="/prevPage" />">
                       <spring:message code="label.prev" />
                    </a>
                    </li>
                    
                    <c:if test="${!empty pages}">
                        <c:forEach items="${pages}" var="depositPage">
                            <c:choose>
                                <c:when test="${depositPage.active}">
                                    <li class="active"><a hewf="#">${depositPage.index}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="page" href="<c:url value="/goto/${depositPage.index}" />">${depositPage.index}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li>
                          <a class="page" href="<c:url value="/nextPage" />"><spring:message code="label.next" /></a>
                        </li>
                    </c:if>
                </ul>
<!--Galery-->
                <c:if test="${!empty pictures}">
					<div>
						<c:forEach items="${pictures}" var="picture">
							<div>
								<c:if test="${authorized}">
									<a href="<c:url value="/delete/${picture.id}" />">
									   <spring:message code="label.delete" />
									</a>
								</c:if>
							</div>
							<a href='<c:url value="${picture.path}"/>' class="highslide" onclick="return hs.expand(this)"> 
								<img src="${picture.thumbnail}" alt="${picture.filename}" title="Click to enlarge" />
							</a>
							<div class="highslide-caption">${picture.description}</div>
							<br>
							<table>
								<tr>
									<td>Original</td>
									<td><a href='<c:url value="${picture.path}"/>' target="_blank">Download</a></td>
								<tr>
								<tr>
									<td>Resolution</td>
									<td></td>
								<tr>
								<tr>
									<td>Format</td>
									<td></td>
								<tr>
							</table>
							<br>
						</c:forEach>
					</div>
				</c:if>
				
				<ul class="pagination">
					<li>
					<a href="<c:url value="/goto/0" />"><spring:message code="label.begin" /></a>
					</li>
					<li> 
					<a class="page" href="<c:url value="/prevPage" />">
					   <spring:message code="label.prev" />
					</a>
					</li>
					
					<c:if test="${!empty pages}">
						<c:forEach items="${pages}" var="depositPage">
							<c:choose>
								<c:when test="${depositPage.active}">
									<li class="active"><a hewf="#">${depositPage.index}</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="page" href="<c:url value="/goto/${depositPage.index}" />">${depositPage.index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li>
						  <a class="page" href="<c:url value="/nextPage" />"><spring:message code="label.next" /></a>
						</li>
					</c:if>
				</ul>
			</div>
			</div>
    <footer class="footer">
      <div class="container">
        <p class="text-muted">Copyright © 2014 <a href="skellag@gmail.com">Oleksii Bobko</a>. All rights reserved.</p>
      </div>
    </footer>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>    
</body>
</html>