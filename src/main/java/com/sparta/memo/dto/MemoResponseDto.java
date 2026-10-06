package com.sparta.memo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;
}
