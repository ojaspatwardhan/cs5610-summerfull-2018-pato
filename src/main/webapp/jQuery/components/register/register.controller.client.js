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
      userService.register(user).then(success);
    }

    function success(users) {
      alert(users.length);
    }
  }
}());
