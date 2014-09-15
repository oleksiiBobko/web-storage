<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Album</title>
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
</head>
<body class="content">
	<div class="add">
		<a href="<c:url value="/pictures" />"><spring:message code="label.album" /></a>
		<form:form method="post" action="save.html" commandName="picture"
			enctype="multipart/form-data">
			<!--form:errors path="*" cssClass="error"/-->
			<table>
				<tr>
					<td><form:label path="owner"><spring:message code="label.author" /></form:label></td>
					<td><form:input path="owner" /></td>
				</tr>
				<tr>
					<td><form:label path="description"><spring:message code="label.description" /></form:label></td>
					<td><form:textarea path="description" /></td>
				</tr>
				<tr>
					<td><form:label path="path"><spring:message code="label.picture" /></form:label></td>
					<td><input type="file" name="file" id="file"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="<spring:message code="label.add" />" /></td>
				</tr>
			</table>
		</form:form>
		<br />
	</div>
</body>
</html>