package com.example.crud.RequestDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BookCreationRequest {

    // primary key(id)가 없는이유 : book에서
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    이 어노테이션이 역할을 대신한다. 즉, bookRepository.save(book) 할 때 DB가 자동으로 고유한 id 값을 생성함
    private String isbn;
    private String name;
//    private Long authorId;

    //책 추가할때 저자이름
    private String firstName; // 추가
    private String lastName;  // 추가
}

