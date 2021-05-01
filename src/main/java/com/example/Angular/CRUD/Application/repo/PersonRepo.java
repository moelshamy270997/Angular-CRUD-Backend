package com.example.Angular.CRUD.Application.repo;

import com.example.Angular.CRUD.Application.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findPersonById(Long id);

}
