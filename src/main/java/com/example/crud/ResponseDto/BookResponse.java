package com.example.crud.ResponseDto;

import com.example.crud.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private String isbn;
//    private Long authorId; 책을 만드는데 있어서
    private String firstName;
    private String lastName;


    // Entity -> ResponseDto 변경 why? 컨트롤러에서 엔티티를 가지고 움직이면 안됨
    public static BookResponse of (Book book) {
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getIsbn(),
                book.getAuthor() != null ? book.getAuthor().getFirstName() : null,
                book.getAuthor() != null ? book.getAuthor().getLastName() : null

//                book.getAuthor() != null ? book.getAuthor().getId() : null
        );
    }

    // 전체 책 조회할때 List<Book>을 stream()으로 읽어서 BookResponse형태로 바꾸고 collect로 List<BookResponse> 형태의 리스트를 면경
    public static List<BookResponse> fromList(List<Book> books) {
        return books.stream()
                .map(BookResponse::of)
                .collect(Collectors.toList());
    }

}
