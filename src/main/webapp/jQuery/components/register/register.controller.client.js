(function() {

  $(main);

  function main() {

    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var userService = new UserServiceClient();

    $usernameFld = $("#usernameFld");
    $passwordFld = $("#passwordFld");
    $verifyPasswordFld = $("#verifyPasswordFld");

    $("#registerButton").click(function() {
      var user = {
        username: $usernameFld.val(),
        password: $passwordFld.val(),
      };
      register(user);
    });

    function register(user) {
      var userService = new UserServiceClient();
      userService.register(user,success);
    }

    function resetValues() {
      $("#usernameFld").val("");
      $("#passwordFld").val("");
      $("#verifyPasswordFld").val("");
    }

    function success(response) {
      if(response.id == 0) {
        alert("Username already exists");
        resetValues();
      }
      else {
        window.location = "http://localhost:8080/jQuery/components/profile/profile.template.client.html";
      }
    }
  }
}());
