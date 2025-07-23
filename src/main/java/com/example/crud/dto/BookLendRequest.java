package com.example.crud.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookLendRequest {
    private List<Long> bookIds;
    private Long memberId;
}
