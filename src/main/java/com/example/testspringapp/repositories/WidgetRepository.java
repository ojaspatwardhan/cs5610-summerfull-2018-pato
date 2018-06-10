package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.testspringapp.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

}
