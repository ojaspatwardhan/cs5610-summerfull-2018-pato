(function() {
  'use strict';
  jQuery(main);

  var tbody;
  var repeat;

  function main() {

    // Local Host
    // var promise = fetch("http://localhost:8080/api/user");

    // Heroku
    var promise = fetch("https://cs5610-summer-2018-pat-ojas.herokuapp.com/api/user")

    tbody = $("tbody");
    repeat = $(".user-credentials")
    promise.then(function (response) {
      return response.json();
    }).then(renderUsers)

    $("#createNewUser").click(createUser)
  }

  function createUser() {
    var firstName = $("#firstNameFld").val();
    var lastName = $("#lastNameFld").val();
    var username = $("#usernameFld").val();
    var password = $("#passwordFld").val();

    var user = {
      username: username,
      firstName: firstName,
      lastName: lastName,
      password: password
    };

    // Heroku
    fetch("https://cs5610-summer-2018-pat-ojas.herokuapp.com/api/user", {
      method: "POST",
      body: JSON.stringify(user),
      headers: {
        "content-type": "application/json"
      }
    });

    // Local Host
    // fetch("http://localhost:8080/api/user", {
    //   method: "POST",
    //   body: JSON.stringify(user),
    //   headers: {
    //     "content-type": "application/json"
    //   }
    // });
  }

  function renderUsers(users) {
    for(var i = 0; i < users.length; i++) {
      var user = users[i];
      var newEntry = repeat.clone();
      newEntry.find(".userName").html(user.username);
      newEntry.find(".firstName").html(user.firstName);
      newEntry.find(".lastName").html(user.lastName);
      newEntry.find(".password").html(user.password);
      tbody.append(newEntry);
    }
  }
}());
