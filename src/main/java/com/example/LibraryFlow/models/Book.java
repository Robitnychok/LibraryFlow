package com.example.LibraryFlow.models;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Book {

    private long id;

    private Long id_person;

    @NotEmpty(message = "Please enter the title of the book")
    @Size(min = 1, max = 50, message = "The title of the book should be between 1 and 50 characters")
    private String title;

    @NotEmpty(message = "Please identify the author of the book")
    @Size(min = 2, max = 50, message = "The author of the book should be between 2 and 50 characters")
    private String author;

    @Min(value = 100, message = "The year of publication cannot be earlier than 100")
    @Max(value = 2025, message = "The year of publication cannot be in the future")
    private int publicationYear;


    public Book(){};

    public Book (long id, String title, String author, int publicationYear){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
