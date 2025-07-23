package com.example.crud.Service;

import com.example.crud.dto.AuthorCreationRequest;
import com.example.crud.dto.BookLendRequest;
import com.example.crud.model.Author;
import com.example.crud.model.Book;
import com.example.crud.model.Member;
import com.example.crud.repository.AuthorRepository;
import com.example.crud.repository.BookRepository;
import com.example.crud.repository.LendRepository;
import com.example.crud.repository.MemberRepository;
import com.example.crud.dto.BookCreationRequest;
import com.example.crud.dto.MemberCreationRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    // @RequiredArgsConstructor로 DI 해주기
    private final BookRepository bookRepository;
    private final LendRepository lendRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;


    // Service 기능 구현.

    // 고유 책 id 값으로 검색
    public Book readBookById(Long id){

        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("" +
                        "Cant find any book under given ID"));


//        Optional<Book> book = bookRepository.findById(id);
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("" +
//                        "Cant find any book under given ID"));
//        return book;
//        Book book = bookRepository.findById(id)
//                .orElse(new Book());
    }


    // isbn값으로 책 검색
    public Book readBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("" +
                        "Cant find any book under given isbn"));
    }

//    readBooks(): 데이터베이스에 저장된 모든 도서를 읽습니다.
    public List<Book> readAllBooks(){
        return bookRepository.findAll();
    }

//    createBook(BookCreationRequest book): BookCreationRequest로 도서를 생성합니다.
    public Book createBook(BookCreationRequest bookCreationRequest){
        Author author =  authorRepository.findById(bookCreationRequest.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("" +
                        "Author Not Found"));
        Book book = new Book();
        BeanUtils.copyProperties(bookCreationRequest, book);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

//    deleteBook(String id): id를 기준으로 도서를 삭제합니다.
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
//    createMember(MemberCreationRequest request): MemberCreationRequest로 회원을 생성합니다.
    public Member createMember(MemberCreationRequest  memberCreationRequest){
        Member member = new Member();
        BeanUtils.copyProperties(memberCreationRequest, member);
        return memberRepository.save(member);
    }
//    updateMember (Long id, MemberCreationRequest request): id에 해당하는 회원을 Member Creation Request로 변경합니다.
    public Member updateMember(Long id, MemberCreationRequest memberCreationRequest){
        Member member = memberRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Member Not Found"));

        BeanUtils.copyProperties(memberCreationRequest, member);
        return memberRepository.save(member);
    }
//    createAuthor (AuthorCreationRequest request): AuthorCreationRequest로 저자를 생성합니다.
    public Author createAuthor(AuthorCreationRequest authorCreationRequest){
        Author author = new Author();
        BeanUtils.copyProperties(authorCreationRequest, author);
        return authorRepository.save(author);
    }
//    lendABook : 여러개의 책을 빌릴때
    public List<String> lendBook(BookLendRequest bookLendRequest){
        //최종 책 빌릴것 리스트
        List<String> Borrow_book = new ArrayList<>();

        //사용자DB에 요청하는사용자가 있는지
        Member memberFindId = memberRepository.findById(bookLendRequest.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member Not Found"));

        //요청들어온 책 하나씩 판단하기
        bookLendRequest.getBookIds().forEach(bookRequest -> {
            //요청된 책이 DB에 있는지
            Book bookFindId = bookRepository.findById(bookRequest)
                    .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

            //만약 있다면 대출되는지 확인해야함



        });

        return Borrow_book;
    }
}
