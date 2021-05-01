package com.example.Angular.CRUD.Application.service;

import com.example.Angular.CRUD.Application.model.Person;
import com.example.Angular.CRUD.Application.repo.PersonRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;
    private AutoCloseable autoCloseable;
    private PersonService personService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        personService = new PersonService(personRepo);
    }

    @AfterEach
    void tearDown() throws Exception {
        //autoCloseable.close();
    }

    @Test
    void findAllPersonsPassed() {
        // when
        personService.findAllPersons();

        // then
        verify(personRepo).findAll();
    }

    @Test
    void createPerson() {
        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");
        person.setId(1L);


        // when
        personService.createPerson(person);

        // then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepo).save(personArgumentCaptor.capture());

        Person capturePerson = personArgumentCaptor.getValue();

        assertThat(capturePerson).isEqualTo(person);
    }

    @Test
    void updatePersonPassed() {

        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");
        person.setLastName("Ahmed");

        // when
        personService.updatePerson(person);

        // then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepo).save(personArgumentCaptor.capture());
        Person capturePerson = personArgumentCaptor.getValue();
        assertThat(capturePerson).isEqualTo(person);
    }

    @Test
    void updatePersonFailed() {

        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");
        person.setLastName("Ahmed");

        // when
        personService.updatePerson(person);

        // then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepo).save(personArgumentCaptor.capture());
        Person capturePerson = personArgumentCaptor.getValue();
        assertThat(capturePerson).isNotEqualTo(Optional.empty());
    }

    @Test
    void deletePersonPassed() {

        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");

        // when
        when(personRepo.findPersonById(person.getId())).thenReturn(Optional.of(person));

        // then
        personService.deletePerson(person.getId());
        verify(personRepo).deleteById(person.getId());
    }

    @Test
    void deletePersonFailed() {

        // given
        Person person = new Person("Mohamed", "Elshamy", "mohamed@gmail.com");

        // when
        given(personRepo.findPersonById(anyLong())).willReturn(Optional.ofNullable(null));

        // then
        personService.deletePerson(person.getId());
    }
}
