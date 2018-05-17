package com.example.testspringapp.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.testspringapp.models.User;
import com.example.testspringapp.repositories.UserRepository;

@RestController
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User updatedUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setDob(updatedUser.getDob());
			user.setEmail(updatedUser.getEmail());
			user.setFirstName(updatedUser.getFirstName());
			user.setLastName(updatedUser.getLastName());
			user.setUsername(updatedUser.getUsername());
			user.setPhone(updatedUser.getPhone());
			user.setRole(updatedUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@RequestMapping(value = "/api/user/findUserByUsername/{username}", method = RequestMethod.GET)
	public List<User> findUserByUsername(@PathVariable("username") String username) {
		return (List<User>) repository.findUserByUsername(username);
	}
	
	@RequestMapping(value = "/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		session.setAttribute("id", user);
		List<User> data = repository.findUserByUsername(user.getUsername());
		if(data.size() == 0) {
			return repository.save(user);
		}
		else {
			return null;
		}
	}
}