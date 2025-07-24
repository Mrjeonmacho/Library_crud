package com.example.crud.repository;

import com.example.crud.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//JpaRepository를 활용하기 위해서 JpaRepository에 엔티티와 ID를 지정해주어야 합니다.
//나머지 Book, Lend 그리고 Member는 위 코드의 반복입니다.
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstNameAndLastName(String firstname, String lastname);
}
