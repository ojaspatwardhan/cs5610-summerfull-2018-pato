(function() {
  'use strict';
  $(init);

  var $email;
  var $username;
  var $phone;
  var $role;
  var $dob;
  var $firstName;
  var $lastName;
  var $updateButton;
  var id;


  function init() {
    var userService = new UserServiceClient();

    getProfile();

    // findUserById(272);

    $email = $("#email");
    $username = $("#username");
    $phone = $("#phone");
    $role = $("#role");
    $dob = $("#dob");
    $firstName = $("#firstName");
    $lastName = $("#lastName");
    $updateButton = $("#updateButton").click(updateUser);
    $("#logoutBtn").click(logout);

  }

  function logout() {
    var userService = new UserServiceClient();
    userService.logout(logoutMessage);
  }

  function logoutMessage(response) {

    // localhost Location
    window.location = "http://localhost:8080/jQuery/components/login/login.template.client.html";

    // Heroku location
    // window.location = "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/login/login.template.client.html";
    alert(response);
  }

  function getProfile() {
    var userService = new UserServiceClient();

    userService.getProfile(function(response){
      findUserById(response.id)
      id = response.id;
    });
  }

  function updateUser() {
    var userService = new UserServiceClient();

    alert(id);

    var user = {
      firstName: $firstName.val(),
      lastName: $lastName.val(),
      email: $email.val(),
      phone: $phone.val(),
      role: $role.val(),
      dob: $dob.val(),
      username: $username.val()
    };

    userService.updateUser(user, id).then(success);
  }

  function success(response) {
    if(response != null) {
      alert("Success");
    }
    else {
      alert("Unable to update");
    }
  }

  function findUserById(userId) {
    var userService = new UserServiceClient();
    userService.findUserById(userId).then(renderUser);
  }

  function renderUser(user) {
    console.log(user);
    $email.val(user.email);
    $username.val(user.username);
    $phone.val(user.phone);
    $role.val(user.role);
    $dob.val(user.dob);
    $firstName.val(user.firstName);
    $lastName.val(user.lastName);
  }
}());
