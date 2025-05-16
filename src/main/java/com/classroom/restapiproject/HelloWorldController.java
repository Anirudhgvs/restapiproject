package com.classroom.restapiproject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @GetMapping("/class")
    public String getMessage(){
        return "Java and Spring boot batch says hello from my app!!!";
    }

    //Get, Put, Post, Delete - Http Methods

}
