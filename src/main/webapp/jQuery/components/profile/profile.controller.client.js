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
  var password;


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
    // window.location = "http://localhost:8080/jQuery/components/login/login.template.client.html";

    // Heroku location
    window.location = "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/login/login.template.client.html";
    alert(response);
  }

  function getProfile() {
    var userService = new UserServiceClient();

    userService.getProfile(function(response){
      findUserById(response.id)
      id = response.id;
      password = response.password
    });
  }

  function updateUser() {
    var userService = new UserServiceClient();

    var user = new User();
    user.setId(id);
    user.setFirstName($("#firstName").val());
    user.setLastName($("#lastName").val());
    user.setDob($("#dob").val());
    user.setEmail($("#email").val());
    user.setUsername($("#username").val());
    user.setPhone($("#phone").val());
    user.setRole($("#role").val());
    user.setPassword(password);

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
