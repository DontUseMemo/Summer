package com.example.oppu.Perfume;

import com.example.oppu.Perfume.DTO.PerfumeDto;
import com.example.oppu.Perfume.Entity.PerfumeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class PerfumeController {
    private final PerfumeRepository perfumeRepo;
    @Autowired
    public PerfumeController(PerfumeRepository perfumeRepo) {
        this.perfumeRepo = perfumeRepo;
    }

    @GetMapping("/Perfume/perfumeList")
    public String GOperfumeList(){
        return "Perfume/perfumeList";
    }

    @PostMapping("/Perfume/perfumeList")
        public String perfumeList(PerfumeDto perfume){
        ArrayList<PerfumeInfo> perfumeInfo = new ArrayList<>();


    }

}
