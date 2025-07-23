package com.example.crud.ResponseDto;

import com.example.crud.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private String isbn;

    public static BookResponse of (Book book) {
        return new BookResponse(book.getId(), book.getName(), book.getIsbn());
    }
}
