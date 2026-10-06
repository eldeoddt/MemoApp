package com.sparta.memo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // class - table 매핑
public class Memo {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;
    private String username;
    private String contents;
}