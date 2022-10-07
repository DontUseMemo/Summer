package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MagazineService {

    //매거진작성 메소드
    void insertMagazine(Magazine magazine);

    //매거진상세 메소드
    Magazine getMagazine(Magazine magazine);

    //메거진수정 메소드
    void updateMagazine(Magazine magazine);

    //매거진삭제 메소드
    void deleteMagazine(Magazine magazine);

    //매거진페이징 검색 메소드
    Page<Magazine> findAll(Pageable pageable);

    //매거진페이징 제목 검색 메소드
    Page<Magazine> findByTitle(Pageable pageable, String keyword);

    //매거진페이징 내용 검색 메소드
    Page<Magazine> findByContent(Pageable pageable, String keyword);

    //매거진페이징 작성자 검색 메소드
    Page<Magazine> findByWriter(Pageable pageable, String keyword);

}
