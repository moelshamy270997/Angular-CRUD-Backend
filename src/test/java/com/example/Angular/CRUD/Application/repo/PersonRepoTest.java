package com.example.Angular.CRUD.Application.repo;

import com.example.Angular.CRUD.Application.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepoTest {

    @Autowired
    private PersonRepo personRepo;


    @AfterEach
    void tearDown() {
        personRepo.deleteAll();
    }

    @Test
    void findPersonByIdPassed() {
        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");

        Optional<Person> optionalPerson = Optional.of(person);

        // when
        personRepo.save(person);

        // then
        Optional<Person> expected = personRepo.findPersonById(person.getId());
        assertThat(expected).isEqualTo(optionalPerson);

    }

    @Test
    void findPersonByIdFailed() {
        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");

        Optional<Person> optionalPerson = Optional.of(person);

        personRepo.save(person);

        // when
        Optional<Person> expected = personRepo.findPersonById((long) -1);

        // then
        assertThat(expected).isNotEqualTo(optionalPerson);

    }
}
