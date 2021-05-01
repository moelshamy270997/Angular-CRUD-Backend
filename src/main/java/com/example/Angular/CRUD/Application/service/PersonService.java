package com.example.Angular.CRUD.Application.service;

import com.example.Angular.CRUD.Application.exception.PersonNotFoundException;
import com.example.Angular.CRUD.Application.model.Person;
import com.example.Angular.CRUD.Application.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> findAllPersons() {
        return personRepo.findAll();
    }

    public Person createPerson(Person person) {
        return personRepo.save(person);
    }

    public Person readPerson(Long id) {
        return personRepo.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person by id " + id + " was not found"));
    }

    public Person updatePerson(Person person) {
        return personRepo.save(person);
    }

    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }

}
