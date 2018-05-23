(function() {
  'use strict';
  $(init);


  function init() {

    var $usernameFld = $("#usernameFld");
    var $passwordFld = $("#passwordFld");

    $("#loginBtn").click(function() {

      if(validateInputs() == true) {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var user = new User("" ,"", "", username, password, "", "", "", "");
        // var user = {
        //   username: $usernameFld.val(),
        //   password: $passwordFld.val()
        // };

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
      if(response.role == "Student" || response.role == "Faculty") {
        // localhost location
        // window.location = "http://localhost:8080/jQuery/components/profile/profile.template.client.html";

        // Heroku Location
        window.location = "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/profile/profile.template.client.html";
      }
      else {
        // localhost Location
        // window.location = "http://localhost:8080/jQuery/components/user-admin/user-admin.template.client.html";

        // Heroku Location
        window.location = "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/user-admin/user-admin.template.client.html";
      }
    }
    else {
      alert("Please enter valid username and password");
      resetValues();
    }
  }
}());
