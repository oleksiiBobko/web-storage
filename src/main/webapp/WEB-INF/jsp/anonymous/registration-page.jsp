<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
	<div class="panel-body">
		<form:form method="POST" modelAttribute="user" role="form">
			<div class="form-group">
				<input type="text" name="first_name" id="first_name"
					class="form-control input-sm" placeholder="First Name">
			</div>
			<div class="form-group">
				<input type="text" name="last_name" id="last_name"
					class="form-control input-sm" placeholder="Last Name">
			</div>
			<div class="form-group">
				<input type="email" name="email" id="email"
					class="form-control input-sm" placeholder="Email Address">
			</div>
			<div class="form-group">
				<input type="password" name="password" id="password"
					class="form-control input-sm" placeholder="Password">
			</div>
			<div class="form-group">
				<input type="password" name="password_confirmation"
					id="password_confirmation" class="form-control input-sm"
					placeholder="Confirm Password">
			</div>
			<input type="submit" value="Create an account"
				class="btn btn-info btn-block" disabled>
		</form:form>

	</div>
</div>

<!--     <div class="row" align="center"> -->
<%--         <sf:form method="POST" modelAttribute="user" role="form"> --%>
<!--             <div class="col-lg-6" align="center"> -->
<!--                 <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div> -->
<!--                 <div class="form-group"> -->
<!--                     <label for="InputName">Enter Name</label> -->
<!--                     <div class="input-group"> -->
<%--                         <sf:input type="text" class="form-control" path="login" placeholder="Enter Name" /> --%>
<!--                         <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span> -->
<%--                         <sf:errors path="login" cssClass="error"/> --%>
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="form-group"> -->
<!--                     <label for="InputEmail">Enter Email</label> -->
<!--                     <div class="input-group"> -->
<%--                         <sf:input type="email" path="email" class="form-control" id="InputEmailFirst" name="InputEmail" placeholder="Enter Email" /> --%>
<!--                         <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="form-group"> -->
<!--                     <label for="passwd">Enter Password</label> -->
<!--                     <div class="input-group"> -->
<%--                         <sf:input type="password" path="pw" id="InputPasswordFirst" class="form-control"/> --%>
<!--                         <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span> -->
<!--                     </div> -->
<!--                 </div> -->
                
<!--                 <div class="form-group"> -->
<!--                     <label for="passwd">Confirm Password</label> -->
<!--                     <div class="input-group"> -->
<!--                         <textfield type="password" id="InputPasswordSecond" class="form-control"></textfield> -->
<!--                         <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span> -->
<!--                     </div> -->
<!--                 </div> -->
                
<%--                 <input type="submit" value="<spring:message code="label.registration"/>" class="btn btn-info pull-right" /> --%>
<!--             </div> -->
<%--     </sf:form> --%>
<!--     </div> -->