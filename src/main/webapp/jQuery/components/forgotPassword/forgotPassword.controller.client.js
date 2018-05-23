(function() {
  'use strict';

  $(init);

  function init() {
    $("#resetPasswordBtn").click(function() {
      var email = $("#forgotPasswordEmail").val();
      var user = new User("" ,"", "", "", "", "", "", "", email);
      // var user = {
      //   email: email
      // };
      resetPassword(user);
    });
  }

  function resetPassword(user) {
    var userService = new UserServiceClient();
    userService.findUserByEmail(user.email, redirect);
  }

  function redirect(response) {
    if(response.length != 0) {
      // localhost location
      // window.location = "http://localhost:8080/jQuery/components/login/login.template.client.html";

      // Heroku Location
      window.location = "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/login/login.template.client.html"
    }
    else {
      alert("Enter a valid email");
    }
  }

}());
