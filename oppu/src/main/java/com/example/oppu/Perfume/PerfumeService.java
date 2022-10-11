package com.example.oppu.Perfume;

import com.example.oppu.DataNotFoundException;
import com.example.oppu.Perfume.Entity.*;
import com.example.oppu.Perfume.Repository.*;
//import com.example.oppu.Perfume.Repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {

//    private final PerfumeRepository perfumeRepo;
    private final PerfumeInfoRepository perfumeInfoRepo;
    private final PerfumerRepository perfumerRepo;
    private final PerfumeNoteRepository perfumeNoteRepo;

    private final AllNoteRepository allNoteRepo;
    private final AccordsRepository accordsRepo;



    @Autowired
    public PerfumeService(PerfumeInfoRepository perfumeInfoRepo, PerfumerRepository perfumerRepo, PerfumeNoteRepository perfumeNoteRepo, AllNoteRepository allNoteRepo, AccordsRepository accordsRepo) {
//        this.perfumeRepo = perfumeRepo;
        this.perfumeInfoRepo = perfumeInfoRepo;
        this.perfumerRepo = perfumerRepo;
        this.perfumeNoteRepo = perfumeNoteRepo;
        this.allNoteRepo = allNoteRepo;
        this.accordsRepo = accordsRepo;
    }
    //향수 상세페이지
   public PerfumeInfo getPerfumeInfo(String name ) {
       PerfumeInfo infos = perfumeInfoRepo.findByPerfumeName(name);
       if (infos == null) {
           throw new DataNotFoundException(("존재하지 않음"));
       }
       return infos;
   }
    //향수 리스트
   public List<PerfumerList> getPerfumerList(String name){
       return perfumerRepo.findByPerfumeName(name);
   }
    //Top 노트
   public List<PerfumeNote> getPerfumeTop(String name){
       return perfumeNoteRepo.findTop(name);
   }
   //Middle 노트
    public List<PerfumeNote> getPerfumeMiddle(String name){
        return perfumeNoteRepo.findMiddle(name);

    }
    //Base 노트
    public List<PerfumeNote> getPerfumeBase(String name){
        return perfumeNoteRepo.findBase(name);

    }
    //상세 노트
    public AllNote getAllNote(String name){
        return allNoteRepo.findByNoteName(name);
    }

    //향수 삭제
    public void DeletePerfume(PerfumeInfo perfumeInfo){
        perfumeInfoRepo.findById(perfumeInfo.getPerfumeName());
    }

    public void insertPerfume(PerfumeNote perfumeNote,PerfumerList perfumerList,String perfumer){
        perfumeNoteRepo.save(perfumeNote);
        perfumerList.setPerfumer(perfumer);
        perfumerRepo.save(perfumerList);
    }
    //페이징
    public Page<PerfumeInfo> findAll(Pageable pageable) {
        System.out.println("-----service findAll-----");
        return perfumeInfoRepo.findAll(pageable);
    }

    // 향수 검색 메소드
    public Page<PerfumeInfo> findByPerfumeName(Pageable pageable, String keyword) {
        System.out.println("-----service findByTitle-----");
        return perfumeInfoRepo.findByPerfumeNameContainingIgnoreCase(pageable, keyword);
    }
    //브랜드 검색
    public Page<PerfumeInfo> findByBrand(Pageable pageable, String keyword) {
        System.out.println("-----service findByTitle-----");
        return perfumeInfoRepo.findByBrandContainingIgnoreCase(pageable, keyword);
    }


    //Accords
    public List<Accords> getAccords(String name){
        return accordsRepo.findByPerfumeName(name);

    }





}

