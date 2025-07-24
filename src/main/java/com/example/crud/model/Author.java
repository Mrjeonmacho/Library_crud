package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "author")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {
//  @Id:    이 필드가 **기본 키(PK)**라는 뜻입니다.
//  @GeneratedValue(strategy = GenerationType.IDENTITY):
//  Auto Increment 방식으로 id 값을 자동 생성 (MySQL에서 보통 사용).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    //저자의 Model은 PK(Primary Key)와 firstName, lastName필드를 갖습니다.


    // 🔽 5-1 관계 설정: Book → Author (N:1)
    @JsonBackReference
    @OneToMany(mappedBy = "author",
    fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;
}
