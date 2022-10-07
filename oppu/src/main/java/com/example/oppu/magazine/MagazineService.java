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

    //매거진검색 메소드
    Magazine getMagazineWhereTitleOrWriterOrContent(String title, String writer, String content);

    //매거진페이징 메소드
    Page<Magazine> getMagazineList(int page);

//    public Page<Magazine> findByTitle(Pageable pageable, String keyword);
//
//    public Page<Magazine> findByContent(Pageable pageable, String keyword);
//
//    public Page<Magazine> findByWriter(Pageable pageable, String keyword);

}
