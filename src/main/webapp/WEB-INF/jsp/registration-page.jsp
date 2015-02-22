<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title><spring:message code="label.title"/></title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="resources/js/script.js"></script>

</head>

<body>
<div class="container">
    <div class="row">
        <sf:form method="POST" modelAttribute="user" role="form">
            <div class="col-lg-6">
                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                <div class="form-group">
                    <label for="InputName">Enter Name</label>
                    <div class="input-group">
                        <sf:input type="text" class="form-control" path="login" placeholder="Enter Name" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        <sf:errors path="login" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Enter Email</label>
                    <div class="input-group">
                        <sf:input type="email" path="email" class="form-control" id="InputEmailFirst" name="InputEmail" placeholder="Enter Email" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Confirm Email</label>
                    <div class="input-group">
                        <sf:input type="email" path="email" class="form-control" id="InputEmailSecond" name="InputEmail" placeholder="Confirm Email" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwd">Enter Password</label>
                    <div class="input-group">
                        <sf:input type="password" path="pw" id="InputPasswordFirst" class="form-control"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="passwd">Confirm Password</label>
                    <div class="input-group">
                        <sf:input type="password" path="pw" id="InputPasswordSecond" class="form-control"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>                
                
                <input type="submit" value="<spring:message code="label.registration"/>" class="btn btn-info pull-right" />
            </div>
        </sf:form>
        <div class="col-lg-5 col-md-push-1">
            <div class="col-md-12">
                <div class="alert alert-success">
                    <strong><span class="glyphicon glyphicon-ok"></span> Success! Message sent.</strong>
                </div>
                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-remove"></span><strong> Error! Please check all page inputs.</strong>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<!-- <body> -->

<!--     <span><a href="?lang=en">en</a>|<a href="?lang=ru">ru</a></span> -->

<!--     <div> -->
<!--     <div> -->
<%--         <h1><a href="<c:url value="/login" />">Album</a></h1> --%>
<!--     </div> -->
<!--     </div> -->
<%--     <c:if test="${not empty param.error}"><font color="red"><spring:message code="label.registererror"/></font></c:if> --%>

<!--     <div id="container"> -->
<!--         <div id="content"> -->
<!--             <div class="wrapper"> -->
<%--             <form:form method="post" action="adduser.html" commandName="user" onsubmit="return validate(this);"> --%>

<!--                 <table> -->
<!--                     <tbody> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                             <p> -->
<!--                                 <strong> -->
<!--                                     <label for="login"> -->
<%--                                         <spring:message code="label.login"/> --%>
<!--                                     </label> -->
<!--                                 </strong> -->
<!--                             </p> -->
<%--                             <form:input class="inputtext" id="login" path="login"/> --%>
<!--                             <input class="public-button" type="button" onclick="check_login();" value=<spring:message code="label.check"/>> -->
<!--                         </td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                         <p> -->
<!--                             <strong> -->
<!--                                 <label for="password"> -->
<%--                                     <spring:message code="label.password"/> --%>
<!--                                 </label> -->
<!--                             </strong> -->
<!--                         </p> -->
<%--                         <form:input path="pw" type="password" id="password" name="password" class="inputtext"/></td> --%>
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                             <p> -->
<!--                             <strong> -->
<!--                                 <label for="confirm-password"> -->
<%--                                     <spring:message code="label.confirm"/> --%>
<!--                                 </label> -->
<!--                             </strong> -->
<!--                         </p> -->
<!--                             <input type="password" id="confirm-password" name="confirm-password" class="inputtext"/></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                             <p> -->
<!--                                 <strong> -->
<!--                                     <label for="confirm-password"> -->
<%--                                         <spring:message code="label.email"/> --%>
<!--                                     </label> -->
<!--                                 </strong> -->
<!--                             </p> -->
<!--                             <input type="text" id="email" name="email" class="inputtext"/> -->
<!--                         </td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                             <p> -->
<!--                                 <strong> -->
<!--                                     <label for="capture"> -->
<%--                                         <spring:message code="label.capture"/> --%>
<!--                                     </label> -->
<!--                                 </strong> -->
<!--                             </p> -->
<!--                             <input type="text" id="capture" name="capture" class="inputtext"/> -->
<!--                         </td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                             <p> -->
<!--                                 <a href="#">Forgot password?</a> -->
<%--                                 <span style="padding: 0 3px;">|</span> <a href="<c:url value="/login" />"><spring:message code="label.login" /></a> --%>
<!--                             </p> -->
<!--                         </td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td colspan="2" align="right"> -->
<%--                             <input type="submit" value="<spring:message code="label.registration" />" class="public-button"/> --%>
<!--                         </td> -->
<!--                     </tr> -->
<!--                     </tbody> -->
<!--                 </table> -->
<%--             </form:form> --%>
<!--         </div> -->
<!--     </div> -->
<!--     </div> -->

<!--     <div> -->
<!--         <div> -->
<!--             <p id="copyright"> -->
<!--                 Copyright © 2014 <a href="#">Oleksii Bobko</a>. All rights reserved. -->
<!--             </p> -->
<!--         </div> -->
<!-- </div> -->
<!-- </body> -->
<!-- </html> -->