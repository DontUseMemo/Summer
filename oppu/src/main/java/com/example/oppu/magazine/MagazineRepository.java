package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    //페이징 및 검색 (제목, 작성자, 내용 검색 가능)
    @Query(value = "SELECT M FROM Magazine M WHERE M.deleteYN = 'N'")
    Page<Magazine> findAll(Pageable pageable);

    Page<Magazine> findByTitleContaining(Pageable pageable, String keyword);
    Page<Magazine> findByContentContaining(Pageable pageable, String keyword);
    Page<Magazine> findByWriterContaining(Pageable pageable, String keyword);


}