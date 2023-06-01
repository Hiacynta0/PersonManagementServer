package com.example.restrsi.controller;

import com.example.restrsi.Person;
import com.example.restrsi.repository.PersonRepositoryImplementation;
import com.example.restrsi.exception.BadRequestEx;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {
    private final PersonRepositoryImplementation dataRepo = new PersonRepositoryImplementation();

    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> getAllPersons() {
        List<EntityModel<Person>> persons = dataRepo.getAllPersons().stream()
                .map(person -> EntityModel.of(person,
                        linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class).updatePerson(person)).withRel("update")))
                .toList();
        return CollectionModel.of(persons,
                linkTo(methodOn(PersonController.class).getAllPersons()).withSelfRel());
    }

    @GetMapping("persons/size")
    public int getSize() {
        return dataRepo.getSize();
    }

    @GetMapping("/persons/{id}")
    public EntityModel<Person> getPerson(@PathVariable int id) {
        Person p = dataRepo.getPerson(id);
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).updatePerson(p)).withRel("update"),
                linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
        );
    }

    @DeleteMapping("/persons/{id}")
    public boolean deletePerson(@PathVariable int id) {
        return dataRepo.deletePerson(id);
    }

    @PostMapping("/persons")
    public EntityModel<Person> addPerson(@RequestBody Person person) throws BadRequestEx {
        Person p = dataRepo.addPerson(person);
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).addPerson(person)).withSelfRel(),
                linkTo(methodOn(PersonController.class).getPerson(person.getId())).withRel("get person"),
                linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
        );
    }

    @PutMapping("/persons")
    public EntityModel<Person> updatePerson(@RequestBody Person person) {
        Person p = dataRepo.updatePerson(person);
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).updatePerson(person)).withSelfRel(),
                linkTo(methodOn(PersonController.class).getPerson(person.getId())).withRel("get person"),
                linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
        );
    }
}
