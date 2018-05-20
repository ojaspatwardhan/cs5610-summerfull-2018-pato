(function() {
  'use strict';
  $(init);


  function init() {

    var $usernameFld = $("#usernameFld");
    var $passwordFld = $("#passwordFld");

    $("#loginBtn").click(function() {

      if(validateInputs() == true) {
        var user = {
          username: $usernameFld.val(),
          password: $passwordFld.val()
        };

        login(user);
      }
      else {
        alert("Enter username and password");
      }
    });
  }

  function validateInputs() {
    var $usernameFld = $("#usernameFld");
    var $passwordFld = $("#passwordFld");
    if($usernameFld.val() == "" || $passwordFld.val() == "") {
      return false;
    }
    else {
      return true;
    }
  }

  function login(user) {
    var userService = new UserServiceClient();

    userService.login(user.username, user.password, success);
  }

  function resetValues() {
    $("#usernameFld").val("");
    $("#passwordFld").val("");
  }

  function success(response) {
    if(response.length != 0) {
      window.location = "http://localhost:8080/jQuery/components/profile/profile.template.client.html";
    }
    else {
      alert("Enter valid username and password");
      resetValues();
    }
  }
}());
