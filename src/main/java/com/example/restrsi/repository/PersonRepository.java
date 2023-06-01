package com.example.restrsi.repository;

import com.example.restrsi.Person;
import com.example.restrsi.exception.BadRequestEx;
import com.example.restrsi.exception.PersonNotFoundEx;

import java.util.List;

interface PersonRepository {
    List<Person> getAllPersons();

    int getSize();
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(Person person) throws PersonNotFoundEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson (Person person) throws BadRequestEx;

}
