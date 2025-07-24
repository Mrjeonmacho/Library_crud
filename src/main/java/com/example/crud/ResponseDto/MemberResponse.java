package com.example.crud.ResponseDto;

import com.example.crud.model.Member;
import com.example.crud.model.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private MemberStatus status;

    // Entity → DTO
    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getStatus()
        );
    }
}
