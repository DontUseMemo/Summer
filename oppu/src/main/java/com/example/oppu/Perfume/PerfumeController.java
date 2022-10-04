package com.example.oppu.Perfume;

import com.example.oppu.Perfume.Entity.PerfumeInfo;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PerfumeController {

    //    private final PerfumeRepository perfumeRepo;
    private final PerfumeService perfumeService;
    private final PerfumeInfoRepository perfumeInfoRepo;

    @Autowired
    public PerfumeController(PerfumeService perfumeService, PerfumeInfoRepository perfumeInfoRepo) {
        this.perfumeService = perfumeService;
        this.perfumeInfoRepo = perfumeInfoRepo;
    }


    @GetMapping("/perfume/perfumeList")
    public String perfumeList(Model model) {

        List<PerfumeInfo> perfumeList =perfumeInfoRepo.findAll();

        System.out.println("========================================");
        System.out.println(perfumeList);
        System.out.println(perfumeList);
        System.out.println("========================================");
        model.addAttribute("perfumeList", perfumeList);

        return "perfume/perfumeList";
    }

//    @PostMapping ("/perfume/perfumeList")
//        public String perfumeList(Model model){
//        List<PerfumeInfo> perfumeList =
//                perfumeService.getPerfumeList();
//        System.out.println("========================================");
//        System.out.println(perfumeList);
//        System.out.println("========================================");
//        model.addAttribute("perfumeList", perfumeList );
//
//
//        return "redirect:/perfume/perfumeList";
//

//}

}
