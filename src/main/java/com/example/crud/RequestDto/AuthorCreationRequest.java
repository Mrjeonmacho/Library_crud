package com.example.crud.RequestDto;

import com.example.crud.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorCreationRequest {

    private String firstName;
    private String lastName;

//    public AuthorCreationRequest(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }

    // BookCreationRequest → AuthorCreationRequest 변환 메서드
//    public static AuthorCreationRequest of(String firstName, String lastName) {
//        return new AuthorCreationRequest(firstName, lastName);
//    }
    public static AuthorCreationRequest of(BookCreationRequest bookRequest) {
        return new AuthorCreationRequest(bookRequest.getFirstName(), bookRequest.getLastName());
    }
}
