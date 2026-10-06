package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자 자동 생성
@RequestMapping("/api")
public class MemoController {

    private final MemoService memoService;

    // create
    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto request) {

        MemoResponseDto responseDto = memoService.createMemo(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    // view
    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> getMemo() {

        List<MemoResponseDto> responseDtoList = memoService.getMemos();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDtoList);
    }

    // mod
    @PutMapping("/memos/{id}")
    public ResponseEntity<Long> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {


        Long updatedId = memoService.updateMemo(id, requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedId);
    }

    // del
    @DeleteMapping("/memos/{id}")
    public ResponseEntity<Long> deleteMemo(@PathVariable Long id) {

        Long deletedId = memoService.deleteMemo(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deletedId);
    }
}
