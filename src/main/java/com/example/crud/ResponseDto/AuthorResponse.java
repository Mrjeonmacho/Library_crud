package com.example.crud.ResponseDto;


import com.example.crud.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
public class AuthorResponse {
    private Long id;
    private String firstName;
    private String lastName;

//    Entity -> dto
    public static AuthorResponse of(Author author) {
//        log.info(author.getId() +"");
        return new AuthorResponse(
                author.getId(),
                author.getFirstName(),
                author.getLastName()
        );
    }
    public Author toEntity() {
        return Author.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }
}
