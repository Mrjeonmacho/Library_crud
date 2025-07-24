package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "book")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    //도서 모델은 PK 도서명, ISBN번호 필드를 갖습니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String isbn;

    // 🔽 5-1 관계 설정: Book → Author (N:1)
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;


//    같은 책이 풍부한 도서관이여서 하나의 책을 여러명이 대출할 수
//    있다는 가상의 상황을 가정하곘습니다. 책과 대출은 1:N 관계입니다.
    @JsonBackReference
    @OneToMany(mappedBy = "book",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lend> lends;
}

