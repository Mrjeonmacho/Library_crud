package com.example.crud.repository;

import com.example.crud.model.Book;
import com.example.crud.model.Lend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, String status);
}
