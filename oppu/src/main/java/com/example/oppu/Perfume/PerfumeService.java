package com.example.oppu.Perfume;

import com.example.oppu.DataNotFoundException;
import com.example.oppu.Perfume.Entity.AllNote;
import com.example.oppu.Perfume.Entity.PerfumeInfo;
import com.example.oppu.Perfume.Entity.PerfumeNote;
import com.example.oppu.Perfume.Entity.PerfumerList;
import com.example.oppu.Perfume.Repository.AllNoteRepository;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;
//import com.example.oppu.Perfume.Repository.PerfumeRepository;
import com.example.oppu.Perfume.Repository.PerfumeNoteRepository;
import com.example.oppu.Perfume.Repository.PerfumerRepository;
import com.example.oppu.magazine.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerfumeService {

//    private final PerfumeRepository perfumeRepo;
    private final PerfumeInfoRepository perfumeInfoRepo;
    private final PerfumerRepository perfumerRepo;
    private final PerfumeNoteRepository perfumeNoteRepo;

    private final AllNoteRepository allNoteRepo;



    @Autowired
    public PerfumeService(PerfumeInfoRepository perfumeInfoRepo, PerfumerRepository perfumerRepo, PerfumeNoteRepository perfumeNoteRepo, AllNoteRepository allNoteRepo) {
//        this.perfumeRepo = perfumeRepo;
        this.perfumeInfoRepo = perfumeInfoRepo;
        this.perfumerRepo = perfumerRepo;
        this.perfumeNoteRepo = perfumeNoteRepo;
        this.allNoteRepo = allNoteRepo;
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



//검색
//public JSONArray autoSearch(String searchValue) throws IOException {
//
//
//    JSONArray arrayObj = new JSONArray();
//    JSONObject jsonObj = null;
//    ArrayList<String> resultlist = new ArrayList<String>();
//
//    // JPA 기능 사용, 포함 단어 검색 메서드인 findByMovieTitleContains();를 이용해도 괜찮음
//    List<Movie> movies = movieRepository.findByMovieTitleStartsWith(searchValue, Sort.by(Sort.Direction.ASC, "movieTitle"));
//
//    for(Movie movie : movies) {
//        String str = movie.getMovieTitle();
//        resultlist.add(str);
//    }
//    //뽑은 후 json파싱
//    for(String str : resultlist) {
//        jsonObj = new JSONObject();
//        jsonObj.put("data", str);
//        arrayObj.add(jsonObj);
//    }
//
//    return arrayObj;
//
//}





}

