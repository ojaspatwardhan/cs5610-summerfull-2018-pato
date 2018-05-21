function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.findUserById = findUserById;
   this.deleteUser = deleteUser;
   this.updateUser = updateUser;
   this.findUserByUsername = findUserByUsername;
   this.register = register;
   this.getProfile = getProfile;
   this.login = login;
   this.findUserByEmail = findUserByEmail;
   this.logout = logout;
   this.url ='http://localhost:8080/api/user';
   var self = this;

   function logout(callback) {
     $.ajax({
       async: false,
       method: "POST",
       url: "/api/logout",
       success: callback
     })
   }

   function findUserByEmail(email, callback) {
     $.ajax({
       async: false,
       method: "GET",
       contentType: "application/json; charset=utf-8",
       url: "/api/resetPassword/" + email,
       success: callback
     })
   }


   // function login(username, password) {
   //   return fetch("http://localhost:8080/api/login/findUserByUsernameAndPassword/" + username + "/" + password).then(function (response) {
   //     if(response.headers.get("content-length") != 0) {
   //       return response.json();
   //     }
   //     else {
   //       return null;
   //     }
   //   });
   // }

   function login(username, password, callback) {
     $.ajax({
       async: false,
       method: "GET",
       contentType: "application/json; charset=utf-8",
       url: "/api/login/findUserByUsernameAndPassword/" + username + "/" + password,
       success: callback,
     })
   }

   function getProfile(callback) {
     $.ajax({
              async: false,
              method: "GET",
              contentType: "application/json; charset=utf-8",
              url: "/api/profile",
              success: callback,
          });
   }

   // function getProfile() {
   //   return fetch("http://localhost:8080/api/profile", {
   //     credentials: 'same-origin'
   //   }).then(function (response) {
   //     return response.json();
   //   });
   // }

   function register(user, callback) {
     $.ajax({
       async: false,
       type: "POST",
       contentType : 'application/json; charset=utf-8',
       dataType : 'json',
       url: "/api/register",
       data: JSON.stringify(user),
       success: callback
     })
   }

   // function register(user) {
   //   return fetch("http://localhost:8080/api/register", {
   //     method: "POST",
   //     body: JSON.stringify(user),
   //     headers: {
   //       "content-type": "application/json"
   //     }
   //   }).then(function (response) {
   //     return response.json();
   //   });
   // }

//    function register(User) {
//     var registerRes = $.ajax({
//         async: false,
//         type: "POST",
//         contentType : 'application/json; charset=utf-8',
//         dataType : 'json',
//         url: "/api/register",
//         data: JSON.stringify(User),
//     })
//
//     return registerRes.responseJSON
//
// }

   function findUserById(userId) {
     return fetch("/api/user/" + userId).then(function(response) {
       return response.json();
     });
   }

   function findUserByUsername(username) {
     return fetch("/api/user/findUserByUsername/" + username).then(function(response) {
       return response.json();
     });
   }

   function updateUser(user, userId) {
     return fetch("/api/user/" + userId, {
       method: "PUT",
       body: JSON.stringify(user),
       headers: {
         "content-type": "application/json"
       }
     }).then(function(response) {
       if(response.status == 200) {
         return response.json();
       }
       else {
         return null;
       }
     });
   }

   function findAllUsers() {
     return fetch("/api/user")
     .then(function (response) {
       return response.json();
     });
   }

   function createUser(user) {
     // Heroku
     // fetch(this.url, {
     //   method: "POST",
     //   body: JSON.stringify(user),
     //   headers: {
     //     "content-type": "application/json"
     //   }
     // });

     // Local Host
     return fetch("/api/user", {
       method: "POST",
       body: JSON.stringify(user),
       headers: {
         "content-type": "application/json"
       }
     });
   }

   function deleteUser(userId) {
     return fetch("/api/user/" + userId, {
       method: "DELETE"
     });
   }
}
