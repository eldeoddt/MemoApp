package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemoRepository extends JpaRepository <Memo, Long> {

    // jpa repository를 상속받으면 기본 메서드 자동 생성 가능. <관리할 엔티티 클래스, pk타입>
    // ex) findbyid, deletebyid
}
