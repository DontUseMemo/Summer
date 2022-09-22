package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    //매거진 제목 작성자 내용 검색
    @Query(value = "SELECT M FROM Magazine M WHERE M.title = :title or M.writer = :writer or M.content = :content")
    Magazine findMagazineByTitleOrWriterOrContent(String title, String writer, String content);

    //deleteYN의 value N일 경우만 데이터 가져오기
//    @Query(value = "SELECT M.deleteYN FROM Magazine M WHERE M.deleteYN = 'N'")
//    Magazine findMagazineByDeleteYnWhereN(String deleteYN);


    //특정 검색조건
//    @Query(value = "select m from Member m where m.email like concat('%',:email,'%')")
//    List<Member> findMemberByEmail(String email);

    //페이징
//    @Query(value = "SELECT M FROM Magazine M WHERE M.deleteYN = 'N'")
//    @Query(value = "SELECT M FROM Magazine M WHERE M.deleteYN IS NOT NULL")
    Page<Magazine> findAll(Pageable pageable);

}