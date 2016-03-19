<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
    <div class="panel-body">
        <form class="submit_form" name="reset_password" action="reset_password" method="POST">
            <div class="form-group">
                <input id="email" placeholder="Email address or nickname" class="form-control input-sm" name="email" type="text">
            </div>
            <div class="form-group">
                <input class="btn btn-info btn-block submit" type="submit" value="reset" />
            </div>
        </form>
        <div class='result'></div>
    </div>
</div>
<script>
</script>