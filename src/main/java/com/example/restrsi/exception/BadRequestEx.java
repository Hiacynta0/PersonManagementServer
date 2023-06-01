package com.example.restrsi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestEx extends RuntimeException {
    public BadRequestEx() {
        super("Istnieje już taka osoba");
    }
    public BadRequestEx(int id) {
        super(String.valueOf(id));
    }
}
