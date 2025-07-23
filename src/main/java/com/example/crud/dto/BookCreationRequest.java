package com.example.crud.dto;

import lombok.Data;

@Data
public class BookCreationRequest {
    private String isbn;
    private String name;
    private Long authorId;
}
