<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet"  href="<spring:url value="resources/css/bootstrap.min.css"/>"/>
    <script type="text/javascript" src="resources/js/script.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<table id="mainTable">
    <tr>
        <td>
        	<tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td>
        	<tiles:insertAttribute name="body" />
        </td>
    </tr>
</table>
</body>
</html>