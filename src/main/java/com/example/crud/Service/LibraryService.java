package com.example.crud.Service;

import com.example.crud.model.Book;
import com.example.crud.repository.AuthorRepository;
import com.example.crud.repository.BookRepository;
import com.example.crud.repository.LendRepository;
import com.example.crud.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    // @RequiredArgsConstructor로 DI 해주기
    private final BookRepository bookRepository;
    private final LendRepository lendRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        readBookById(10L);
    }

    public static Book createNewBook() {
        System.out.println("LibraryService.createNewBook");
        return new Book();
    }

    // Service 기능 구현.
    public static Book readBookById(Long id){
//        Optional<Book> book = bookRepository.findById(id);
//        return bookRepository.findById(id)
//                .orElseGet(Book::new);

        Book newBook = new Book();
        newBook.setName("name");
        Book book = Optional.of(newBook).orElse(createNewBook());
        System.out.println(book.getName());


//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("" +
//                        "Cant find any book under given ID"));
//        return book;
//        Book book = bookRepository.findById(id)
//                .orElse(new Book());

        return book;
    }

//    readBooks(): 데이터베이스에 저장된 모든 도서를 읽습니다.
//    createBook(BookCreationRequest book): BookCreationRequest로 도서를 생성합니다.
//    deleteBook(String id): id를 기준으로 도서를 삭제합니다.
//    createMember(MemberCreationRequest request): MemberCreationRequest로 회원을 생성합니다.
//    updateMember (String id, MemberCreationRequest request): id에 해당하는 회원을 Member Creation Request로 변경합니다.
//    createAuthor (AuthorCreationRequest request): AuthorCreationRequest로 저자를 생성합니다.
//    lendABook (BookLendRequest request): BookLendRequest로 도서를 대출합니다.
}
