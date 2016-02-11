<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                    <label for="passwd">Enter Password</label>
                    <div class="input-group">
                        <sf:input type="password" path="pw" id="InputPasswordFirst" class="form-control"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="passwd">Confirm Password</label>
                    <div class="input-group">
                        <sf:input type="password" path="pw" id="InputPasswordSecond" class="form-control" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                
                <input type="submit" value="<spring:message code="label.registration"/>" class="btn btn-info pull-right" />
            </div>
            <a href="<c:url value='/login'/>"><spring:message code="label.login" /></a>
        </sf:form>
<!--         <div class="col-lg-5 col-md-push-1"> -->
<!--             <div class="col-md-12"> -->
<!--                 <div class="alert alert-success"> -->
<!--                     <strong><span class="glyphicon glyphicon-ok"></span> Success! Message sent.</strong> -->
<!--                 </div> -->
<!--                 <div class="alert alert-danger"> -->
<!--                     <span class="glyphicon glyphicon-remove"></span><strong> Error! Please check all page inputs.</strong> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
    </div>
</div>