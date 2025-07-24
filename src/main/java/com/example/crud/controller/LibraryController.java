package com.example.crud.controller;

import com.example.crud.RequestDto.AuthorCreationRequest;
import com.example.crud.RequestDto.BookCreationRequest;
import com.example.crud.RequestDto.MemberCreationRequest;
import com.example.crud.ResponseDto.AuthorResponse;
import com.example.crud.ResponseDto.BookResponse;
import com.example.crud.ResponseDto.MemberResponse;
import com.example.crud.Service.LibraryService;
import com.example.crud.model.Author;
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

    //    @GetMapping("/book") // isbn값으로 책 검색, 전체 검색
//    public ResponseEntity<BookResponse> readBooks(@RequestParam(required = false)String isbn){// 전체반환
//        return ResponseEntity.ok(libraryService.readBookByIsbn(isbn)); // 특정반환
//    }

    @GetMapping("/book") //모든 책 반환
    public ResponseEntity<List<BookResponse>> readBookList(){
        return ResponseEntity.ok(libraryService.readAllBooks());
    }

    @GetMapping("/book/{isbn}") // isbn값으로 책 검색, 전체 검색
    public ResponseEntity<BookResponse> readBooks(@PathVariable String isbn){// 전체반환
        return ResponseEntity.ok(libraryService.readBookByIsbn(isbn)); // 특정반환
    }

    @GetMapping("/book/{bookId}") // Id값으로 책 검색
    public ResponseEntity<BookResponse> readBook (@PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.readBookById(bookId));
    }

    @PostMapping("/book") // 책 추가
    public ResponseEntity<BookResponse> createBook(@RequestBody BookCreationRequest bookCreationRequest) {
        return ResponseEntity.ok(libraryService.createBook(bookCreationRequest));
    }

    @DeleteMapping("/book/{bookId}") // 책 삭제
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        libraryService.deleteBookById(bookId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/author") // 저자 추가
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorCreationRequest authorCreationRequest) {
        return ResponseEntity.ok(libraryService.createAuthor(authorCreationRequest));
    }

    @PostMapping("/member") // 사용자 추가
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberCreationRequest memberCreationRequest) {
        return ResponseEntity.ok(libraryService.createMember(memberCreationRequest));

    }
    @PatchMapping("/member/{memberId}") // 사용자 업데이트
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId,@RequestBody MemberCreationRequest memberCreationRequest) {
//        return ResponseEntity<MemberResponse>.ok(libraryService.updateMember(memberId, memberCreationRequest));
        return null;
    }
//    @PostMapping("/book/lend") // 책 빌리기
}

