<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<style>
</style>
</head>
<body class=content>
	<c:if test="${not empty param.error}">
		<font color="red"> <spring:message code="label.loginerror" />
			: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</font>
	</c:if>
	</br>
	<hr>
	<div class="login">
		<a href="<c:url value="/pictures" />"><spring:message
				code="label.album" /></a> <br />
		<form method="POST"
			action="<c:url value="/j_spring_security_check" />">
			<table>
				<tr>
					<td align="right"><spring:message code="label.login" /></td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td align="right"><spring:message code="label.password" /></td>
					<td><input type="password" name="j_password" /></td>
				</tr>

				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="<spring:message code="label.login" />" /> <input
						type="reset" value="<spring:message code="label.delete" />" /></td>
					<td colspan="2" align="right"></td>
				</tr>

			</table>
		</form>
		<a href="<c:url value="/registration" />"><spring:message
				code="label.registration" /></a>
	</div>
</body>
</html>