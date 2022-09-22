package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MagazineServiceImpl implements MagazineService{

    private final MagazineRepository magazineRepository;

    public MagazineServiceImpl(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    //매거진작성 메소드
    @Override
    public void insertMagazine(Magazine magazine) {
        magazine.setCreateDate(new Date());
        magazine.setUpdateDate(new Date());
        magazineRepository.save(magazine);
    }

    //매거진목록 메소드
//    @Override
//    public List<Magazine> getMagazineList(Magazine magazine) {
//        return (List<Magazine>) magazineRepository.findAll();
//    }

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
        magazineRepository.deleteById(magazine.getId());
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
        Pageable pageable = PageRequest.of(page, 2, Sort.by(sorts));
        return magazineRepository.findAll(pageable);
    }

//    @Override
//    public Magazine deleteMagazineYN(Magazine magazine) {
//        Magazine deleteYN = magazineRepository.findAll(magazine.getDeleteYN()).get();
//
//        Magazine yn = magazine.getDeleteYN();
//
//        return magazineRepository.;
//    }
}
