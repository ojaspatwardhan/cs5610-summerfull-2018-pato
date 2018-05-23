function User(id, firstName, lastName, username, password, role, dob, phone, email) {
  this.id = id;
  this.username = username;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  this.phone = phone;
  this.email = email;
  this.dob = dob;
  this.role = role;

  this.setId = setId;
  this.getId = getId;

  this.setUsername = setUsername;
  this.getUsername = getUsername;

  this.setPassword = setPassword;
  this.getPassword = getPassword;

  this.setFirstName = setFirstName;
  this.getFirstName = getFirstName;

  this.setLastName = setLastName;
  this.getLastName = getLastName;

  this.setPhone = setPhone;
  this.getPhone = getPhone;

  this.setEmail = setEmail;
  this.getLastName = getEmail;

  this.setDob = setDob;
  this.getDob = getDob;

  this.setRole = setRole;
  this.getRole = getRole;

  function setId(id) {
    this.id = id;
  }
  function getId() {
    return this.id;
  }

  function setUsername(username) {
    this.username = username;
  }
  function getUsername() {
    return this.username;
  }

  function setPassword(password) {
    this.password = password;
  }
  function getPassword() {
    return this.password;
  }

  function setFirstName(firstName) {
    this.firstName = firstName;
  }
  function getFirstName() {
    return this.firstName;
  }

  function setLastName(lastName) {
    this.lastName = lastName;
  }
  function getLastName() {
    return this.lastName;
  }

  function setPhone(phone) {
    this.phone = phone;
  }
  function getPhone() {
    return this.phone;
  }

  function setEmail(email) {
    this.email = email;
  }
  function getEmail() {
    return this.email;
  }

  function setDob(dob) {
    this.dob = dob;
  }
  function getDob() {
    return this.dob;
  }

  function setRole(role) {
    this.role = role;
  }
  function getRole() {
    return this.role;
  }
}
