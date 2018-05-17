function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.findUserById = findUserById;
   this.deleteUser = deleteUser;
   this.updateUser = updateUser;
   this.findUserByUsername = findUserByUsername;
   this.register = register;
   this.url ='http://localhost:8080/api/user';
   var self = this;

   function register(user) {
     return fetch("http://localhost:8080/api/register", {
       method: "POST",
       body: JSON.stringify(user),
       headers: {
         "content-type": "application/json"
       }
     });
   }

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
     return fetch(self.url + "/" + userId).then(function(response) {
       return response.json();
     });
   }

   function findUserByUsername(username) {
     return fetch(self.url + "/findUserByUsername/" + username).then(function(response) {
       return response.json();
     });
   }

   function updateUser(user, userId) {
     return fetch(self.url + "/" + userId, {
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
     return fetch(self.url)
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
     return fetch("http://localhost:8080/api/user", {
       method: "POST",
       body: JSON.stringify(user),
       headers: {
         "content-type": "application/json"
       }
     });
   }

   function deleteUser(userId) {
     return fetch(self.url + "/" + userId, {
       method: "DELETE"
     });
   }
}
