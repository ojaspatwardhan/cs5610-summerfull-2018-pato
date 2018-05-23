(function() {

  $(main);

  function main() {

    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var userService = new UserServiceClient();

    $usernameFld = $("#usernameFld");
    $passwordFld = $("#passwordFld");
    $verifyPasswordFld = $("#verifyPasswordFld");

    $("#registerButton").click(function() {
      var username = $usernameFld.val();
      var password = $passwordFld.val();
      var user = new User("", "", username, password, "", "", "", "");
      // var user = {
      //   username: $usernameFld.val(),
      //   password: $passwordFld.val(),
      // };
      register(user);
    });

    $("#verifyPasswordFld").keyup(function() {
      validatePassword($passwordFld.val());
    });
  }

  function register(user) {
    var userService = new UserServiceClient();
    userService.register(user, success);
  }

  function resetValues() {
    $("#usernameFld").val("");
    $("#passwordFld").val("");
    $("#verifyPasswordFld").val("");
    $(".alert").css("display", "none");
  }

  function validatePassword(verifyPassword) {
    $verifyPasswordFld = $("#verifyPasswordFld");
    $(".alert.alert-danger").show(500);
    if($verifyPasswordFld.val() == verifyPassword) {
      $(".alert").attr("class", "col-md-3 alert alert-success").html("Passwords match");

    }
    else {
      $(".alert").attr("class", "col-md-3 alert alert-danger").html("Passwords do not match")
    }
  }

  function success(response) {
    if(response.id == 0) {
      alert("Username already exists");
      resetValues();
    }
    else {

      // localhost location
      // window.location = "http://localhost:8080/jQuery/components/profile/profile.template.client.html";

      // Heroku Location
      window.location = "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/profile/profile.template.client.html";
    }
  }
}());
