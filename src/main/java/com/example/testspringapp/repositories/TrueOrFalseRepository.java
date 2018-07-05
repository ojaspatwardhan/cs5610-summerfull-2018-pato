package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.testspringapp.models.TrueOrFalse;

public interface TrueOrFalseRepository extends CrudRepository<TrueOrFalse, Integer> {

}
