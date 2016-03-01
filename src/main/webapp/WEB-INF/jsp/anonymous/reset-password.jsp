<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
    <div class="panel-body">
        <form class="reset_password" name="reset_password_form" action="send_reset_password_mail" method="POST">
            <div class="form-group">
                <small>email address</small>
                <input id="email" placeholder="Email address" class="form-control input-sm" name="email" type="text">
            </div>
            <div class="form-group">
                <input class="btn btn-default btn-block submit" type="submit" value="reset" />
            </div>
        </form>
        <div class='result'></div>
    </div>
</div>
<script>
$(document).ready(function() {
    $(".reset_password").submit(function(event) {
        var postData = $(this).serializeArray();
        var formURL = $(this).attr('action');
        var method = $(this).attr('method');
      $.ajax({
          type: method,
          url: formURL,
          data: postData,
          success: function(data, textStatus, jqXHR) {
              console.log('success');
              if(jqXHR.status === 200) {
                  $('.result').html('<div class="alert alert-success" role="alert">email sent</div>');
                  window.setTimeout(function () {
                      $(".alert").fadeTo(500, 0).slideUp(500, function () {
                          $(this).remove();
                      });
                  }, 5000);
              }
            },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log('error');
              $('.result').html('<div class="alert alert-danger" role="alert">error</div>');
              window.setTimeout(function () {
                  $(".alert").fadeTo(500, 0).slideUp(500, function () {
                      $(this).remove();
                  });
              }, 5000);
          }
        });
      return false;
    });
    
});
</script>