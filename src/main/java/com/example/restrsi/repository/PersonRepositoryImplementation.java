package com.example.restrsi.repository;

import com.example.restrsi.MyData;
import com.example.restrsi.Person;
import com.example.restrsi.exception.BadRequestEx;
import com.example.restrsi.exception.PersonNotFoundEx;
import com.example.restrsi.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonRepositoryImplementation implements PersonRepository {
    private List<Person> personList;

    public PersonRepositoryImplementation() {
        this.personList = new ArrayList<>(Arrays.asList(
                new Person(0, "Kamil Nowak", 30, "knowak23@wp.pl"),
                new Person(1, "Anna Kowal", 23, "ania123@gmail.com")
        ));
    }

    @Override
    public List<Person> getAllPersons() {
        System.out.println("Wywołanie metody getAllPersons");
        return personList;
    }

    @Override
    public int getSize() {
        System.out.println("Wywołanie metody getSize");
        return personList.size();
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundEx {
        System.out.println("Wywołanie metody getPerson");
        for (Person person : personList){
            if(person.getId() == id)
                return person;
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person updatePerson(Person person) throws PersonNotFoundEx {
        System.out.println("Wywołanie metody updatePerson");
        int index = -1;
        for (int i = 0; i < personList.size(); i++){
            if(person.getId() == personList.get(i).getId()) {
                index = i;
                break;
            }
        }

        if(index != -1) {
            personList.get(index).setName(person.getName());
            personList.get(index).setAge(person.getAge());
            personList.get(index).setEmail(person.getEmail());
            return personList.get(index);
        }

        throw new PersonNotFoundEx(person.getId());
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        System.out.println("Wywołanie metody deletePerson");
        if(personList.removeIf(person -> person.getId() == id))
            return true;
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person addPerson(Person person) throws BadRequestEx {
        System.out.println("Wywołanie metody addPerson");
        boolean personFound = false;
        for (Person p : personList){
            if (person.getId() == p.getId()) {
                personFound = true;
                break;
            }
        }

        if(!personFound){
            personList.add(person);
            return person;
        }

        throw new BadRequestEx(person.getId());
    }
}
