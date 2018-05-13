package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.testspringapp.models.Hello;

public interface HelloRepository extends CrudRepository<Hello, Integer> {

}
