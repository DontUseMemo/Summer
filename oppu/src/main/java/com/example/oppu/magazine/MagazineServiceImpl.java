package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MagazineServiceImpl implements MagazineService {

    private final MagazineRepository magazineRepository;

    public MagazineServiceImpl(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    //매거진작성 메소드
    @Override
    public void insertMagazine(Magazine magazine) {
        magazine.setCreateDate(LocalDateTime.now());
        magazine.setUpdateDate(LocalDateTime.now());
        magazineRepository.save(magazine);
    }

    //메거진상세 메소드
    @Override
    public Magazine getMagazine(Magazine magazine) {
        return magazineRepository.findById(magazine.getId()).get();
    }

    //매거진수정 메소드
    @Override
    public void updateMagazine(Magazine magazine) {
        Magazine findMagazine = magazineRepository.findById(magazine.getId()).get();

        findMagazine.setTitle(magazine.getTitle());
        findMagazine.setContent(magazine.getContent());
        magazineRepository.save(findMagazine);
    }

    //매거진삭제 메소드
    @Override
    public void deleteMagazine(Magazine magazine) {
        System.out.println(magazine);
        Magazine findMagazine = magazineRepository.findById(magazine.getId()).get();
        findMagazine.setDeleteYN("Y");
        magazineRepository.save(findMagazine);

    }

    //매거진검색 메소드
    @Override
    public Magazine getMagazineWhereTitleOrWriterOrContent(String title, String writer, String content) {
        return magazineRepository.findMagazineByTitleOrWriterOrContent(title, writer, content);
    }

    //매거진페이징 메소드
    @Override
    public Page<Magazine> getMagazineList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return magazineRepository.findAll(pageable);
    }

//    @Override
//    public Page<Magazine> findAll(Pageable pageable){
//        System.out.println("-----service findAll-----");
//        return magazineRepository.findAll(pageable);
//    }
//    @Override
//    public Page<Magazine> findByTitle(Pageable pageable, String keyword) {
//        System.out.println("-----service findByTitle-----");
//        return magazineRepository.findByTitleContaining(pageable, keyword);
//    }
//    @Override
//    public Page<Magazine> findByContent(Pageable pageable, String keyword) {
//        System.out.println("-----service findByContent-----");
//        return magazineRepository.findByContentContaining(pageable, keyword);
//    }
//    @Override
//    public Page<Magazine> findByWriter(Pageable pageable, String keyword) {
//        System.out.println("-----service findByWriter-----");
//        return magazineRepository.findByWriterContaining(pageable, keyword);
}

