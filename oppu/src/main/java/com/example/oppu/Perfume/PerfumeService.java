package com.example.oppu.Perfume;

import com.example.oppu.DataNotFoundException;
import com.example.oppu.Perfume.Entity.PerfumeInfo;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;
//import com.example.oppu.Perfume.Repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfumeService {

//    private final PerfumeRepository perfumeRepo;
    private final PerfumeInfoRepository perfumeInfoRepo;


    @Autowired
    public PerfumeService( PerfumeInfoRepository perfumeInfoRepo) {
//        this.perfumeRepo = perfumeRepo;
        this.perfumeInfoRepo = perfumeInfoRepo;
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


}

