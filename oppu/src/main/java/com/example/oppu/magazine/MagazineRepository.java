package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    //매거진 제목 작성자 내용 검색
    @Query(value = "SELECT M FROM Magazine M WHERE M.title = :title or M.writer = :writer or M.content = :content")
    Magazine findMagazineByTitleOrWriterOrContent(String title, String writer, String content);

    //yn값 설정 (중..)
//    @Query(value = "SELECT M.deleteYN FROM Magazine M WHERE M.deleteYN = 'N' ")
//    Magazine findMagazineByDeleteYnWhereN(String deleteYN);

        //    @Query(value = "SELECT M.deleteYN FROM Magazine M WHERE M.deleteYN = 'N'")
//    Magazine findMagazineByDeleteYnWhereN(String deleteYN);

//n값일 경우에만 목록에 넣기 > 페이징부분 건들여야함 / y값으로 변했을 때 뺴기 >
   //특정 검색조건
//    @Query(value = "select m from Member m where m.email like concat('%',:email,'%')")
//    List<Member> findMemberByEmail(String email);

    //페이징
//    @Query(value = "SELECT M.deleteYN FROM Magazine M WHERE M.deleteYN = 'N' ")
    Page<Magazine> findAll(Pageable pageable);

}