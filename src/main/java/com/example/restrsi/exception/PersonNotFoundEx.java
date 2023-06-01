package com.example.restrsi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundEx extends RuntimeException {
    public PersonNotFoundEx() {
        super("Nie znaleziono osoby");
    }
    public PersonNotFoundEx(int id) {
        super(String.valueOf(id));
    }
}
