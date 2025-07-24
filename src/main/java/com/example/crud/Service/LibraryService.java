package com.example.crud.Service;

import com.example.crud.RequestDto.AuthorCreationRequest;
import com.example.crud.RequestDto.BookLendRequest;
import com.example.crud.ResponseDto.AuthorResponse;
import com.example.crud.ResponseDto.BookResponse;
import com.example.crud.ResponseDto.MemberResponse;
import com.example.crud.model.*;
import com.example.crud.repository.AuthorRepository;
import com.example.crud.repository.BookRepository;
import com.example.crud.repository.LendRepository;
import com.example.crud.repository.MemberRepository;
import com.example.crud.RequestDto.BookCreationRequest;
import com.example.crud.RequestDto.MemberCreationRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public BookResponse readBookById(Long id) {

        return BookResponse.of(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant find any book under given ID")));

//        return bookRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("" +
//                        "Cant find any book under given ID"));


//        Optional<Book> book = bookRepository.findById(id);
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("" +
//                        "Cant find any book under given ID"));
//        return book;
//        Book book = bookRepository.findById(id)
//                .orElse(new Book());
    }


    // isbn값으로 책 검색
    public BookResponse readBookByIsbn(String isbn) {
        return BookResponse.of(bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("" +
                        "Cant find any book under given isbn")));
    }

    //    readBooks(): 데이터베이스에 저장된 모든 도서를 읽습니다.
    public List<BookResponse> readAllBooks() {
        return BookResponse.fromList(bookRepository.findAll());
    }

//    public List<BookResponse> readAllBooks() {
//        return bookRepository.findAll();
//    }

    //    createBook(BookCreationRequest book): BookCreationRequest로 도서를 생성합니다.
    public BookResponse createBook(BookCreationRequest bookCreationRequest) {
        Author newAuthor = authorRepository.findByFirstNameAndLastName(
                bookCreationRequest.getFirstName() , bookCreationRequest.getLastName())
                .orElseGet(() ->
//                    Author newAuthor = Author.builder()
////                            .id(bookCreationRequest.getAuthorId())
//                            .firstName(bookCreationRequest.getAuthorFirstName())
//                            .lastName(bookCreationRequest.getAuthorLastName())
//                            .build();
//                    createAuthor(AuthorCreationRequest.of(bookCreationRequest)));
                                createAuthor(AuthorCreationRequest.of(bookCreationRequest)).toEntity()
                );

        Book newBook = Book.builder()
                .isbn(bookCreationRequest.getIsbn())
                .name(bookCreationRequest.getName())
                .author(newAuthor)
                .build();
        return BookResponse.of(bookRepository.save(newBook));
    }
    //    createAuthor (AuthorCreationRequest request): AuthorCreationRequest로 저자를 생성합니다.
    public AuthorResponse createAuthor(AuthorCreationRequest authorCreationRequest) {
        Author author = Author.builder()
                .firstName(authorCreationRequest.getFirstName())
                .lastName(authorCreationRequest.getLastName())
                .build();
        return AuthorResponse.of(authorRepository.save(author));
    }

    //    deleteBook(String id): id를 기준으로 도서를 삭제합니다.
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }



    //    createMember(MemberCreationRequest request): MemberCreationRequest로 회원을 생성합니다.
    public MemberResponse createMember(MemberCreationRequest memberCreationRequest) {
//        Member member = new Member();
        Member member = Member.builder()
                .firstName(memberCreationRequest.getFirstName())
                .lastName(memberCreationRequest.getLastName())
                .status(MemberStatus.ACTIVE)
                .build();
        return MemberResponse.of(memberRepository.save(member));
    }



    //    updateMember (Long id, MemberCreationRequest request): id에 해당하는 회원을 Member Creation Request로 변경합니다.
    public MemberResponse updateMember(Long id, MemberCreationRequest memberCreationRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member Not Found"));

//        BeanUtils.copyProperties(memberCreationRequest, member);

//        Member member = Member.builder() 빌더는 객체를 생성할때 쓰는거지, 업데이트 할때는 쓰면 안됨
//                .firstName(memberCreationRequest.getFirstName())
//                .lastName(memberCreationRequest.getLastName())
//                .status(memberCreationRequest.getStatus()) // 필요시
//                .build();
        member.updateInfo(
                memberCreationRequest.getFirstName(),
                memberCreationRequest.getLastName()
        );
        return MemberResponse.of(memberRepository.save(member));
    }




    //    lendABook : 여러개의 책을 빌릴때
    public List<String> lendBook(BookLendRequest bookLendRequest) {
        //최종 책 빌릴것 리스트 리턴할거임
        List<String> Borrow_book = new ArrayList<>();

        //사용자DB에 요청하는사용자가 있는지
        Member member = memberRepository.findById(bookLendRequest.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member Not Found"));

        //요청들어온 책 하나씩 판단하기
        bookLendRequest.getBookIds().forEach(bookRequest -> {
            //요청된 책이 DB에 있는지
            Book book = bookRepository.findById(bookRequest)
                    .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

            //만약 있다면 대출되는지 확인해야함
            Optional<Lend> lendOptional = lendRepository.findByBookAndStatus(book, LendStatus.BURROWED);

            if (lendOptional.isEmpty()) {
                Borrow_book.add(book.getName());
                Lend newLend = Lend.builder()
                        .member(member)
                        .book(book)
                        .status(LendStatus.BURROWED)
                        .startOn(Instant.now())
                        .endOn(Instant.now().plus(30, ChronoUnit.DAYS))
                        .build();

                lendRepository.save(newLend);
            }

//            방법1
//            Lend lend = lendRepository.findByBookAndStatus(bookFindId, LendStatus.BURROWED)
//                    .orElse(null);
//            if(lend != null){
//                Borrow_book.add(bookFindId.getName());
//
////                Lend lend1 = Lend.of();
//                Lend newlend = Lend.builder()
//                        .member(memberFindId)
//                        .book(bookFindId)
//                        .status(LendStatus.BURROWED)
//                        .startOn(Instant.now())
//                        .endOn((Instant.now().plus(30, ChronoUnit.DAYS)))
//                        .build();
//                lendRepository.save(newlend);
//
//
            // 나쁜 방식
//                newlend.setMember(memberFindId);
//                newlend.setBook(bookFindId);
//                newlend.setStatus(LendStatus.BURROWED);
//                newlend.setStartOn(Instant.now());
//                newlend.setEndOn(Instant.now().plus(30, ChronoUnit.DAYS));
//                lendRepository.save(newlend);
        });
        return Borrow_book;
    }
}