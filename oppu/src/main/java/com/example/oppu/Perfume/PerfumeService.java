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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    List<PerfumeInfo> getPerfumeList(){
        return perfumeInfoRepo.findByNameAndBrand();
    }

   public PerfumeInfo getPerfumeInfo(String name ) {
       PerfumeInfo infos = perfumeInfoRepo.findByPerfumeName(name);
       if (infos == null) {
           throw new DataNotFoundException(("존재하지 않음"));
       }
       return infos;
   }

   public List<PerfumerList> getPerfumerList(String name){
       return perfumerRepo.findByPerfumeName(name);
   }

   public List<PerfumeNote> getPerfumeTop(String name){
       return perfumeNoteRepo.findTop(name);

   }
    public List<PerfumeNote> getPerfumeMiddle(String name){
        return perfumeNoteRepo.findMiddle(name);

    }
    public List<PerfumeNote> getPerfumeBase(String name){
        return perfumeNoteRepo.findBase(name);

    }

    public List<AllNote> getAllNote(String name){
        return allNoteRepo.findByNoteName(name);
    }




}

