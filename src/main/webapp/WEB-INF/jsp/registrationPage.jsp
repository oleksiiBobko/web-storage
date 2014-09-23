<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="noodp,noydir">
    <meta name="description" content="Survs is a collaborative tool that enables you to create online surveys with simplicity and elegance.">
    <meta name="author" content="Survs">

    <title><spring:message code="label.title"/></title>

    <link href="resources/style/home2.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="resources/js/script.js"></script>

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
            font: normal 15px 'Lucida Grande', 'Lucida Sans Unicode', Lucida, Arial, Helvetica, sans-serif !important;
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

    <span style="float: right"><a href="?lang=en">en</a>|<a href="?lang=ru">ru</a></span>

    <div id="header">
    <div class="wrapper">
        <h1><a href="<c:url value="/login" />">Commercial Bank</a></h1>
    </div>
    </div>

    <c:if test="${not empty param.error}"><font color="red"><spring:message code="label.registererror"/></font></c:if>

    <div id="container">
        <div id="content">
            <div class="wrapper">
            <form:form method="post" action="adduser.html" commandName="user"
                       onsubmit="return validate(this);">

                <table class="login" style="margin-left: auto; margin-right: auto;">
                    <tbody>
                    <tr>
                        <td>
                            <p class="mtop0 mbottom025">
                                <strong>
                                    <label for="usrName">
                                        <spring:message code="label.login"/>
                                    </label>
                                </strong>
                            </p>
                            <form:input class="inputtext" id="usrName" path="usrName"/>
                            <input class="public-button" type="button" onclick="check_login();" value=<spring:message code="label.login"/>>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <p class="mtop05 mbottom025">
                            <strong>
                                <label for="password">
                                    <spring:message code="label.password"/>
                                </label>
                            </strong>
                        </p>
                        <form:input path="pw" type="password" id="password" name="password" class="inputtext"/></td>
                    </tr>
                    <tr>
                        <td>
                            <p class="mtop05 mbottom025">
                            <strong>
                                <label for="confirm-password">
                                    <spring:message code="label.confirm"/>
                                </label>
                            </strong>
                        </p>
                            <input type="password" id="confirm-password" name="confirm-password" class="inputtext"/></td>
                    </tr>
                    <tr>
                        <td>
                            <p class="mtop05 mbottom025">
                                <strong>
                                    <label for="confirm-password">
                                        <spring:message code="label.email"/>
                                    </label>
                                </strong>
                            </p>
                            <input type="text" id="email" name="email" class="inputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="mtop05 mbottom025">
                                <strong>
                                    <label for="capture">
                                        <spring:message code="label.capture"/>
                                    </label>
                                </strong>
                            </p>
                            <input type="text" id="capture" name="capture" class="inputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="mtop025 mbottom0 smalltxt">
                                <a href="/www.survs.com/app/wa/Password/forgot">Forgot password?</a>
                                <span style="padding: 0 3px;">|</span> <a href="<c:url value="/login" />"><spring:message
                                        code="label.login" /></a>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="<spring:message code="label.registration" />" class="public-button"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
    </div>
    </div>

    <div id="footer">
        <div class="wrapper">
            <p>
<!--                 <a href="http://blog.survs.com/"><span>Blog</span></a> <span class="bar">|</span> -->
<!--                 <a href="https://www.survs.com/press/"><span>Press</span></a> <span class="bar">|</span> -->
<!--                 <a href="https://www.survs.com/privacy/"><span>Privacy</span></a> <span class="bar">|</span> -->
<!--                 <a href="https://www.survs.com/tos/"><span>Terms</span></a> <span class="bar">|</span> -->
<!--                 <a href="http://www.twitter.com/survs"><span>Twitter</span></a> <span class="bar">|</span> -->
<!--                 <a href="https://www.survs.com/help/"><span>Help</span></a> <span class="bar">|</span> -->
<!--                 <a href="https://www.survs.com/contact/">Contact</a> -->
            </p>
            <p id="copyright">
<!--                 Copyright © 2014 <a href="http://www.enoughpepper.com/">Enough Pepper Lda</a>. All rights reserved. -->
            </p>
        </div>

</div>

</body>
</html>