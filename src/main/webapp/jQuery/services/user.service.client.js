function UserServiceClient() {
   this.createUser = createUser;
   this.findAllUsers = findAllUsers;
   this.findUserById = findUserById;
   this.deleteUser = deleteUser;
   this.updateUser = updateUser;
   this.url =
'http://localhost:8080/api/user';
   var self = this;

   function findUserById(userId) {
     return fetch(self.url + "/" + userId).then(function(response) {
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
       console.log(response.bodyUsed);
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
