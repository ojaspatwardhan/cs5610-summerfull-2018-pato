package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.testspringapp.models.Essay;

public interface EssayRepository extends CrudRepository<Essay, Integer> {

}
