package com.example.oppu.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUploadInfoRepository extends JpaRepository<FileUploadEntity, Long> {

    //finby 튜플을 찾겠다.
    //boardSeq : boardSeq컬럼에 데이터를 찾겠다.

    List<FileUploadEntity> findByBoardSeq(Long seq);
}