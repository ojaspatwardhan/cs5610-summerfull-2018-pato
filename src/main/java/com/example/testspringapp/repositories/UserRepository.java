package com.example.testspringapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.example.testspringapp.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM user WHERE user.username = :searchTerm", nativeQuery = true)
	List<User>findUserByUsername(@Param("searchTerm") String searchTerm);
	
	@Query(value = "SELECT * FROM user WHERE user.username = :username AND user.password = :password", nativeQuery = true)
	User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query(value = "SELECT * from user WHERE user.email = :email", nativeQuery = true)
	User findUserByEmail(@Param("email") String email);

}
