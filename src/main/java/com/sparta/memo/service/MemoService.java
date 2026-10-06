package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor // 생성자 만들기
@Service
@Transactional
public class MemoService {

    private final MemoRepository memoRepository; // @RequiredArgsConstructor가 있어야 이 형태로 수동 초기화 없이 사용 가능하다.

    // 메모 생성
    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto) {

        // 생성 시에는 builder로 생성
         Memo newMemo = Memo.builder()
                .username(memoRequestDto.getUsername())
                .contents(memoRequestDto.getContents()) // dt에 @Getter가 있어야 사용 가능하다.
                .build();
        Memo savedMemo = memoRepository.save(newMemo); // jpa save(): 저장 후 Memo 객체를 반환한다.

        // 필요한 값만 dto에 담아 전달
        MemoResponseDto responseDto = MemoResponseDto.builder() // dto에 @Builder가 있어야 사용가능하다.
                .id(savedMemo.getId()) // dto에 @Getter가 있어야 사용 가능하다.
                .username(savedMemo.getUsername())
                .contents(savedMemo.getContents())
                .build();

        return responseDto;
    }

    // 메모 조회
    public List<MemoResponseDto>getMemos() {

        // stream으로 list<memo>를 dto list로 변환한다.
        List<MemoResponseDto> responseDtoList = memoRepository.findAll().stream()
                .map(memo -> MemoResponseDto.builder()
                        .id(memo.getId()) // memo 엔티티에 @Getter가 있어야 사용 가능하다.
                        .username(memo.getUsername())
                        .contents(memo.getContents())
                        .build())
                .toList();

        return responseDtoList;
    }

    // 메모 수정
    public Long updateMemo(Long id, MemoRequestDto requestDto) {

        // findbyid -> null pointer exception 방지 예외 처리 필수이다. .orElseThrow( () -> new GeneralException(ErrorStaus.USER_NOT_FOUND) )
        Memo updatedMemo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Memo with id " + id + " not found"));

        // content만 수정: @Setter 활용
        updatedMemo.setContents(requestDto.getContents());

        return updatedMemo.getId();
    }


    // 메모 삭제
    public Long deleteMemo(Long id) {

        // findbyid로 대상 객체 가져오기
        Memo deletedMemo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Memo with id" + id + "not found"));

        // 해당 객체 삭제
        memoRepository.delete(deletedMemo);

        // deletedMemo의 id 반환하기
        return deletedMemo.getId();
    }
}
