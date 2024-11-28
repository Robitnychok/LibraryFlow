package com.example.LibraryFlow.models;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {

    private long id;

    @NotEmpty(message = "Please enter your first and last name")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String fullName;

    @Min(value = 1700, message = "Year of birth cannot be earlier than 1700")
    @Max(value = 2025, message = "Year of birth cannot be in the future")
    private int birthYear;


    public Person(){};

    public Person(long id, String fullName, int birthYear){
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }
}
