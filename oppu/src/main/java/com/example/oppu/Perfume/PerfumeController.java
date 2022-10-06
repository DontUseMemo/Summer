package com.example.oppu.Perfume;

import com.example.oppu.Perfume.Entity.AllNote;
import com.example.oppu.Perfume.Entity.PerfumeInfo;
import com.example.oppu.Perfume.Entity.PerfumeNote;
import com.example.oppu.Perfume.Entity.PerfumerList;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/perfume")
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


    @GetMapping("/perfumeList")
    public String perfumeList(Model model) {

        List<PerfumeInfo> perfumeList =perfumeInfoRepo.findAll();
        model.addAttribute("perfumeList", perfumeList);

        return "/perfume/perfumeList";
    }


//    @GetMapping("/perfumeInfo")
//    public String perfumeInfo(){
////        List<PerfumeInfo> perfumeInfo =perfumeInfoRepo.findAll();
////        model.addAttribute("perfumeInfo", perfumeInfo);
//        return "perfume/perfumeInfo";
//
//    }

    @GetMapping("/perfumeInfo/{perfumeName}")
    public String perfumeInfo(@PathVariable("perfumeName") String name , Model model){
        PerfumeInfo perfumeInfo = perfumeService.getPerfumeInfo(name);
        List<PerfumerList> perfumerList =perfumeService.getPerfumerList(name);
        List<PerfumeNote> perfumeTop = perfumeService.getPerfumeTop(name);
        List<PerfumeNote> perfumemiddle = perfumeService.getPerfumeMiddle(name);
        List<PerfumeNote> perfumebase = perfumeService.getPerfumeBase(name);

        model.addAttribute("perfumeInfo", perfumeInfo);
        model.addAttribute("perfumerList", perfumerList);
        model.addAttribute("perfumeTop", perfumeTop);
        model.addAttribute("perfumeMiddle", perfumemiddle);
        model.addAttribute("perfumeBase", perfumebase);

        return "/perfume/perfumeInfo";

    }

    @GetMapping("/note")
    public String note(){
        return "/perfume/note";
    }

    @GetMapping("/note/{note}")
    public String noteName(@PathVariable("note") String name, Model model){
        List<AllNote> note =perfumeService.getAllNote(name);
        model.addAttribute("Allnote", note);
        return "/perfume/note";
    }


}
