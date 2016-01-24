<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<spring:url value="resources/css/bootstrap.min.css"/>" />
<link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="resources/css/sticky-footer-navbar.css" rel="stylesheet">
<link href="resources/style/highslide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/js/script.js"></script>
<script type="text/javascript" src="resources/js/highslide-with-gallery.js"></script>
<meta http-equiv="Content-Type" content="text/html;">
<style type="text/css">
@media ( min-width : 768px) {
	.navbar .navbar-nav {
		display: inline-block;
		float: none;
		vertical-align: top;
	}
	.navbar .navbar-collapse {
		text-align: center;
	}
}
</style>
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
<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-53f3b27b3fa051c3"></script>        
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
       	<tiles:insertAttribute name="header" />
	<div class="container" style="text-align: center;">
		<tiles:insertAttribute name="pagenation" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="pagenation" />
	</div>
	<footer class="footer">
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
	</footer>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>    
</body>
</html>