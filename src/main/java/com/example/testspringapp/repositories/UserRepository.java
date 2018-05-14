package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.testspringapp.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
