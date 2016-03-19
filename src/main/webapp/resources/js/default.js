function validate(form) {
    var e = form.elements;
    if (e['confirm-password'].value != document.getElementById('password').value) {
        alert('Your passwords do not match. Please type more carefully.');
        return false;
    }

    return true;
}

/*function setRelativelySize(img) {
 if(img.height > 300) {
 img.style.height = '300px';
 img.style.width = 'auto';
 } else if (img.width > 300) {
 img.style.height = 'auto';
 img.style.width = '300px';
 }
 }*/

$(document).ready(function() {
    $(".submit_form").submit(function(event) {
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
                  $('.result').html('<div class="alert alert-success" role="alert">'+ jqXHR.responseText + '</div>');
                  window.setTimeout(function () {
                      $(".alert").fadeTo(500, 0).slideUp(500, function () {
                          $(this).remove();
                      });
                  }, 5000);
              }
            },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log(jqXHR);
              $('.result').html('<div class="alert alert-danger" role="alert">' + jqXHR.responseText + '</div>');
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