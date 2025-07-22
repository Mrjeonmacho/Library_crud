package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;


//    한 명의 회원이 도서 여러권을 대출할 수 있습니다. 회원과 대출은 1:N관계입니다.
    @JsonBackReference
    @OneToMany(mappedBy = "member",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lend> lends;
}
//회원 모델은 PK(Primary Key)와 firstName, lastName
//그리고 회원 상태를 알려주는 Enum 필드를 갖습니다.