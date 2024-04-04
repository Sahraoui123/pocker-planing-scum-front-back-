package com.example.demo.exceptions;

public class UserStoryNotFoundException extends RuntimeException{
    public UserStoryNotFoundException(Long id){
        super("Could not find user story " + id);
    }
}
