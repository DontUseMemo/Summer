package com.example.oppu.Perfume;

import com.example.oppu.Perfume.Entity.PerfumeInfo;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;
//import com.example.oppu.Perfume.Repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    ArrayList<PerfumeInfo> getPerfumeList(){
        return perfumeInfoRepo.findByNameAndBrand();
    }
}
