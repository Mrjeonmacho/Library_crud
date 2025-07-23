package com.example.crud.RequestDto;

import lombok.Data;

import java.util.List;

@Data
public class BookLendRequest {
    private List<Long> bookIds;
    private Long memberId;
}
