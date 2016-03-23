<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ctxt" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta property="og:type" content="website"/>
<meta property="og:title" content="Store your files here"/>
<meta property="og:site_name" content="Free File storage"/>
<meta property="og:description" content="This is the best place to find yourself as a puppy artist. JK)"/>
<meta property="og:image" content="${ctxt}/resources/images/logo.jpg" />
<meta property="og:url" content="${ctxt}"/>

<meta name="twitter:card" content="summary"/>
<meta name="twitter:site" content="@FILES"/>
<meta name="twitter:title" content="Store your files here"/>
<meta name="twitter:description" content="This is the best place to find yourself as a puppy artist. JK)"/>
<meta name="twitter:image" content="${ctxt}/resources/images/logo.jpg"/>
<meta name="twitter:url" content="${ctxt}"/>

<link rel="shortcut icon" href="${ctxt}/resources/favicon.ico" />
<link type="text/css" rel="stylesheet" href="<spring:url value="resources/css/bootstrap.min.css"/>" />
<link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="resources/css/social-sharing.css" rel="stylesheet" type="text/css" />
<link href="resources/css/sticky-footer-navbar.css" rel="stylesheet">
<link href="resources/style/highslide.css" rel="stylesheet" type="text/css" />
<link href="resources/css/default.css" rel="stylesheet" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/default.js"></script>
<script type="text/javascript" src="resources/js/highslide-with-gallery.packed.js"></script>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-53f3b27b3fa051c3"></script>
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
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
<h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
</div>

<div class="modal-body">
    <p>You are about to delete a file this procedure is irreversible.</p>
    <p>Do you want to proceed?</p>
    <p class="debug-url"></p>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
    <a class="btn btn-danger btn-ok">Delete</a>
</div>
</div>
</div>
</div>
<script>
$(document).ready(function() {
$('#confirm-delete').on('show.bs.modal', function(e) {
$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
$('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
});
});
</script>

    <a href="https://github.com/oleksiiBobko/album" class=".visible-xs-block, hidden-xs"><img
        style="position: absolute; top: 50px; right: 0; border: 0;"
        src="https://camo.githubusercontent.com/e7bbb0521b397edbd5fe43e7f760759336b5e05f/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f677265656e5f3030373230302e706e67"
        alt="Fork me on GitHub"
        data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png"></a>
    <tiles:insertAttribute name="header" />
    <div class="container">
        <tiles:insertAttribute name="pagenation" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="pagenation" />
    </div>
    <footer class="footer">
    <div class="container">
        <tiles:insertAttribute name="footer" />
    </div>
    </footer>
    <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
