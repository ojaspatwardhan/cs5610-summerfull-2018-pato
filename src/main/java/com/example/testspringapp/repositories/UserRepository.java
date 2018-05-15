package com.example.testspringapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.testspringapp.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM user WHERE user.username = :searchTerm", nativeQuery = true)
	List<User>findUserByUsername(@Param("searchTerm") String searchTerm);

}
