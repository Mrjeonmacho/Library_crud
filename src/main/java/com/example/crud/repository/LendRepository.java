package com.example.crud.repository;

import com.example.crud.model.Book;
import com.example.crud.model.Lend;
import com.example.crud.model.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
    // JPA가 제공하는 기본적인 메소드 말고 추가 메소드 작성한거임 그냥
}
