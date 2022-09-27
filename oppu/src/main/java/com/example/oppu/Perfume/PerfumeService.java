package com.example.oppu.Perfume;

import com.example.oppu.Perfume.Entity.PerfumeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {
    @Autowired
    private final PerfumeRepository perfumeRepo;

    public PerfumeService(PerfumeRepository perfumeRepo) {
        this.perfumeRepo = perfumeRepo;
    }

    public List<PerfumeInfo> getPerfumeList(){
        return perfumeRepo.findAll();
    }


}
