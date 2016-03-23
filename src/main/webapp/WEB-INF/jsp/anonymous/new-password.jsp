<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<sec:authorize access="hasRole('PASSWORD_UPDATE')">
    <div class="panel-body">
        <form class="submit_form" name="save_password" action="save_password" method="POST">
            <div class="form-group">
                <input type="password" name="pw" id="password"
                    class="form-control input-sm" placeholder="Password">
            </div>
            <div class="form-group">
                <input type="password" name="pwConfirmation"
                    id="password_confirmation"
                    class="form-control input-sm"
                    placeholder="Confirm Password">
            </div>
            <div class="form-group">
                <input class="btn btn-info btn-block submit" type="submit" value="reset" />
            </div>
        </form>
        <div class='result'></div>
    </div>
</div>
</sec:authorize>
