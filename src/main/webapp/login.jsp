<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="robots" content="noodp,noydir">
<meta name="description"
	content="Survs is a collaborative tool that enables you to create online surveys with simplicity and elegance.">
<meta name="author" content="Skell">

<title>Login</title>

<link rel="stylesheet" type="text/css" href="resources/style/home2.css">

<style type="text/css">
.ll-content-notification * {
	letter-spacing: normal !important;
	margin: 0 !important;
	padding: 0 !important;
	background: none !important;
	border: 0 !important;
	float: none !important;
	text-align: left !important;
	text-decoration: none !important;
	font: normal 15px 'Lucida Grande', 'Lucida Sans Unicode', Lucida, Arial,
		Helvetica, sans-serif !important;
}

.ll-content-notification-header-pic img {
	border: 0 !important;
	padding: 0 !important;
	margin: 0 !important;
	line-height: 1px !important;
}
</style>

</head>

<body class="small login">

	<span style="float: right"><a href="?lang=en">en</a>|<a
		href="?lang=ru">ru</a></span>

	<div id="header">
		<div class="wrapper">
			<h1>
				<a href="<c:url value="/login" />">Commercial bank</a>
			</h1>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<div class="wrapper">
				<c:if test="${not empty param.error}">
					<font color="red"> <spring:message code="label.loginerror" />
						: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					</font>
				</c:if>
				<form name="login" method="post"
					action="<c:url value="/j_spring_security_check" />">
					<input value="false" name="returning" id="returning" type="hidden">
					<table class="login" style="margin-left: auto; margin-right: auto;">
						<tbody>
							<tr>
								<td>
									<p class="mtop0 mbottom025">
										<strong> <label for="email"> <spring:message
													code="label.login" />
										</label>
										</strong>
									</p> <input id="email" tabindex="1" class="inputtext"
									name="j_username" type="text">
								</td>
							</tr>
							<tr>
								<td>
									<p class="mtop05 mbottom025">
										<strong> <label for="password">Password</label>
										</strong>
									</p> <input tabindex="2" class="inputtext" name="j_password"
									id="password" type="password">
								</td>
							</tr>

							<tr>
								<td>
									<p class="mtop025 mbottom0 smalltxt">
										<a href="https://www.survs.com/app/wa/Password/forgot">Forgot
											password?</a> <span style="padding: 0 3px;">|</span> <a
											href="<c:url value="/registration" />"><spring:message
												code="label.registration" /></a>
									</p>
								</td>
							</tr>
							<tr>
								<td>
									<p class="mtop025 mbottom0 smalltxt">
										<input name="remember" id="rememberMe" tabindex="3"
											type="checkbox"> <label for="rememberMe">Remember
											me</label>
									</p>
								</td>
							</tr>

							<tr>
								<td colspan="2" align="right"><input class="public-button"
									tabindex="4" type="submit"
									value="<spring:message code="label.login" />" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<!-- wrapper -->
		</div>
		<!-- content -->
	</div>
	<!-- container -->

	<div id="footer">
		<div class="wrapper">
			<p id="copyright">
				Copyright © 2014 <a href="skellag@gmail.com">Oleksii Bobko</a>. All
				rights reserved.
			</p>
		</div>
	</div>
</body>
</html>