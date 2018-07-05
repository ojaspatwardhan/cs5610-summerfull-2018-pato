package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.testspringapp.models.FillInTheBlank;

public interface FillInTheBlankRepository extends CrudRepository<FillInTheBlank, Integer> {

}
