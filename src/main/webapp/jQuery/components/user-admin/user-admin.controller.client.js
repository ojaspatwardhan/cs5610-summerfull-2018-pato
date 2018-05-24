(function() {
  'use strict';
  $(main);

  var tbody;
  var repeat;
  var deleteEntry;

  function main() {

    $("#phoneFld").keyup(function() {
      var mobNum = $(this).val();
      var filter = new RegExp(/^\d*(?:\.\d{1,2})?$/);

      $("#phoneAlert").show(500);

      if(filter.test(mobNum)) {
        if(mobNum.length == 10) {
          $("#phoneAlert").attr("class", "alert alert-success").html("Valid phone number");
        }
        else {
          $("#phoneAlert").attr("class", "alert alert-danger").html("Please enter valid phone number");
        }
      }
      else {
        alert("Invalid");
      }
    });

    $("#emailFld").keyup(function() {
      var emailId = $(this).val();
      var filter = new RegExp(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);

      $("#emailAlert").show(500);

      if(filter.test(emailId)) {
        $("#emailAlert").attr("class", "alert alert-success").html("Valid email");
      }
      else {
        $("#emailAlert").attr("class", "alert alert-danger").html("Please enter valid email");
      }
    });

    $("#phone").keyup(function() {
      var mobNum = $(this).val();
      var filter = new RegExp(/^\d*(?:\.\d{1,2})?$/);

      $("#phoneEditAlert").show(500);

      if(filter.test(mobNum)) {
        if(mobNum.length == 10) {
          $("#phoneEditAlert").attr("class", "alert alert-success").html("Valid phone number");
        }
        else {
          $("#phoneEditAlert").attr("class", "alert alert-danger").html("Please enter valid phone number");
        }
      }
      else {
        alert("Invalid");
      }
    });

    $("#editEmail").keyup(function() {
      var emailId = $(this).val();
      var filter = new RegExp(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);

      $("#emailEditAlert").show(500);

      if(filter.test(emailId)) {
        $("#emailEditAlert").attr("class", "alert alert-success").html("Valid email");
      }
      else {
        $("#emailEditAlert").attr("class", "alert alert-danger").html("Please enter valid email");
      }
    });

    // Local Host
    // var promise = fetch("http://localhost:8080/api/user");

    // Heroku
    // var promise = fetch("https://cs5610-summer-2018-pat-ojas.herokuapp.com/api/user")

    tbody = $("tbody");
    repeat = $(".user-credentials");

    if(window.location.href == "http://localhost:8080/jQuery/components/user-admin/user-admin.template.client.html" || window.location.href == "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/user-admin/user-admin.template.client.html") {
      findAllUsers();
    }

    // findAllUsers();

    $("#createNewUser").click(createUser);
    $("#deleteButton").click(deleteUser);
    // $(".editBtn").click(editUser);
    $("#search").click(function() {
      var username = $("#searchFld").val();
      findUserByUsername(username);
    });

  }

  function findUserByUsername(username) {
    var userService = new UserServiceClient();
    userService.findUserByUsername(username).then(renderUsers);
  }

  function findAllUsers() {
    var userService = new UserServiceClient();
    userService.findAllUsers().then(renderUsers);
  }

  // function findUserById(event) {
  //   var userService = new UserServiceClient();
  //   userService.findUserById(userId).then(renderUser);
  // }

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
      // newEntry.find(".email").html(user.email);
      // newEntry.find(".phone").html(user.phone);
      newEntry.find(".role").html(user.role);
      // newEntry.find(".dob").html(user.dob);
      newEntry.find(".deleteButton").click(deleteUser);
      newEntry.find(".editBtn").click(editUser);
      tbody.append(newEntry);
    }
    resetAddValues();
  }

  function deleteUser(event) {
    var userService = new UserServiceClient();
    var deleteButton = $(event.currentTarget);
    var userId = deleteButton.parent().parent().attr("id");
    userService.deleteUser(userId).then(findAllUsers);
  }

  function editUser(event) {
    var userService = new UserServiceClient();
    var editBtn = $(event.currentTarget);
    var userId = editBtn.parent().parent().attr("id");
    userService.findUserById(userId).then(showProfile);
  }

  // Function to show Profile of the user page after the user is returned by /api/user/userId
  function showProfile(response) {
    $("#firstName").val(response.firstName);
    $("#lastName").val(response.lastName);
    $("#username").val(response.username);
    $("#editEmail").val(response.email);
    $("#dob").val(response.dob);
    $("#password").val(response.password);
    $("#phone").val(response.phone);
    $("#role").val(response.role);
    $("#id").val(response.id)
    $("#updateUser").click(function() {
      updateUser();
    });
  }

  function updateUser(id) {
    var userService = new UserServiceClient();

    var id = $("#id").val();

    var user = new User();
    user.setId($("#id").val());
    user.setFirstName($("#firstName").val());
    user.setLastName($("#lastName").val());
    user.setDob($("#dob").val());
    user.setEmail($("#editEmail").val());
    user.setUsername($("#username").val());
    user.setPhone($("#phone").val());
    user.setRole($("#role").val());
    user.setPassword($("#password").val());

    userService.updateUser(user, id).then(success);
  }

  function resetEditValues() {
    $("#firstName").val("");
    $("#lastName").val("");
    $("#username").val("");
    $("#emailFld").val("");
    $("#dob").val("");
    $("#password").val("");
    $("#phone").val("");
    $("#role").val("");
    $("#id").val("");
    $("#phoneEditAlert").css("display", "none");
    $("#emailEditAlert").css("display", "none");
  }

  function resetAddValues() {
    $("#firstNameFld").val("");
    $("#lastNameFld").val("");
    $("#usernameFld").val("");
    $("#emailFld").val("");
    $("#dobFld").val("");
    $("#passwordFld").val("");
    $("#phoneFld").val("");
    $("#roleFld").val("");
    $("#phoneAlert").css("display", "none");
    $("#emailAlert").css("display", "none");
  }

  function success(response) {
    resetEditValues();
    findAllUsers();
  }
}());
