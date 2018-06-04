package com.example.testspringapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.testspringapp.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

}
