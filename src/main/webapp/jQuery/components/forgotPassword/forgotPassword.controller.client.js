(function() {
  'use strict';

  $(init);

  function init() {
    $("#resetPasswordBtn").click(function() {
      var email = $("#forgotPasswordEmail").val();
      var user = {
        email: email
      };
      resetPassword(user);
    });
  }

  function resetPassword(user) {
    var userService = new UserServiceClient();
    userService.findUserByEmail(user.email);
  }

}());
