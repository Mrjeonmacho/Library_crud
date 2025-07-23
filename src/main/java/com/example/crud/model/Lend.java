package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lend")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant startOn;
    private Instant endOn;

    @Enumerated(EnumType.ORDINAL)
    private LendStatus status;

//    같은 책이 풍부한 도서관이여서 하나의 책을 여러명이 대출할 수
//    있다는 가상의 상황을 가정하곘습니다. 책과 대출은 1:N 관계입니다.
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;


//    한 명의 회원이 도서 여러권을 대출할 수 있습니다. 회원과 대출은 1:N관계입니다.
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonManagedReference
    private Member member;

//    public static Lend of () {
//        return null;
//    }
}
