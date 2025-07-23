package com.example.crud.controller;

import com.example.crud.ResponseDto.BookResponse;
import com.example.crud.Service.LibraryService;
import com.example.crud.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/book") // isbn값으로 책 검색, 전체 검색
    public ResponseEntity<BookResponse> readBooks(@RequestParam(required = false)String isbn){// 전체반환
        return ResponseEntity.ok(libraryService.readBookByIsbn(isbn)); // 특정반환
    }
    @GetMapping("/book/{bookId}") // Id값으로 책 검색
    public ResponseEntity<BookResponse> readBook (@PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.readBookById(bookId));
    }
//    @GetMapping("/bookList") //모든 책 반환
//    public ResponseEntity<List<BookResponse>> readBookList(){
//        return ResponseEntity.ok(libraryService.readAllBooks());
    }

}

