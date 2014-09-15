<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title><spring:message code="label.title" /></title>
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/script.js"></script>
</head>
<body class="content">
	<c:if test="${not empty param.error}">
		<font color="red"> <spring:message code="label.registererror" /></font>
	</c:if>
	</br>
	<hr>
	<div class="join">

		<form:form method="post" action="adduser.html" commandName="user"
			onsubmit="return validate(this);">
			<table>
				<tr>
					<td><spring:message code="label.name" /></td>
					<td><form:input path="usrName" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.password" /></td>
					<td><form:password path="pw" id="password" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.confirm" /></td>
					<td><input type="password" name="confirm-password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit"
						value="<spring:message code="label.registration" />" /></td>
				</tr>
			</table>
			<a href="<c:url value="/login" />"><spring:message
					code="label.login" /></a>
		</form:form>
	</div>
</body>
</html>