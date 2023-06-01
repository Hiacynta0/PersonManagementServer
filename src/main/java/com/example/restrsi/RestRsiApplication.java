package com.example.restrsi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestRsiApplication {

    public static void main(String[] args) {
        MyData.myInfo();
        SpringApplication.run(RestRsiApplication.class, args);
    }

}
