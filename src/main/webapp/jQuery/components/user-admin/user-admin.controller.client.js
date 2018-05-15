(function() {
  'use strict';
  jQuery(main);

  var tbody;
  var repeat;
  var deleteEntry;

  function main() {

    // Local Host
    // var promise = fetch("http://localhost:8080/api/user");

    // Heroku
    // var promise = fetch("https://cs5610-summer-2018-pat-ojas.herokuapp.com/api/user")

    tbody = $("tbody");
    repeat = $(".user-credentials")

    findAllUsers();

    $("#createNewUser").click(createUser);
    $("#deleteButton").click(deleteUser);
    $("#search").click(function() {
      var username = $("#searchFld").val();
      findUserByUsername(username);
    });

  }

  function findAllUsers() {
    var userService = new UserServiceClient();
    userService.findAllUsers().then(renderUsers);
  }

  function findUserById(event) {
    var userService = new UserServiceClient();
    userService.findUserById(userId).then(renderUser);
  }

  function createUser() {

    var userService = new UserServiceClient();

    var firstName = $("#firstNameFld").val();
    var lastName = $("#lastNameFld").val();
    var username = $("#usernameFld").val();
    var password = $("#passwordFld").val();
    var role = $("#roleFld").val();
    var dob = $("#dobFld").val();
    var phone = $("#phoneFld").val();
    var email = $("#emailFld").val();

    var user = {
      username: username,
      firstName: firstName,
      lastName: lastName,
      password: password,
      email: email,
      phone: phone,
      role: role,
      dob: dob
    };

    userService.createUser(user).then(findAllUsers);
  }

  function renderUsers(users) {
    tbody.empty();
    for(var i = 0; i < users.length; i++) {
      var user = users[i];
      var newEntry = repeat.clone();
      newEntry.attr("id", user.id);
      newEntry.find(".userName").html(user.username);
      newEntry.find(".firstName").html(user.firstName);
      newEntry.find(".lastName").html(user.lastName);
      newEntry.find(".password").html(user.password);
      newEntry.find(".deleteButton").click(deleteUser);
      tbody.append(newEntry);
    }
  }

  function deleteUser(event) {
    var userService = new UserServiceClient();
    var deleteButton = $(event.currentTarget);
    var userId = deleteButton.parent().parent().attr("id");
    userService.deleteUser(userId).then(findAllUsers);
  }
}());
