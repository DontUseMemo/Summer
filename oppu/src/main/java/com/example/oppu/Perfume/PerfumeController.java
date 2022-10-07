package com.example.oppu.Perfume;

import com.example.oppu.Perfume.Entity.AllNote;
import com.example.oppu.Perfume.Entity.PerfumeInfo;
import com.example.oppu.Perfume.Entity.PerfumeNote;
import com.example.oppu.Perfume.Entity.PerfumerList;
import com.example.oppu.Perfume.Repository.PerfumeInfoRepository;
import com.example.oppu.magazine.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    //향수 List, Page, 검색
    @GetMapping("/perfumeList")
    public String perfumeList(@RequestParam(value="page", defaultValue = "0") int page,
                              @RequestParam(value = "category",required = false, defaultValue = "")String category,
                              @RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                              Pageable pageable, Model model) {


        List<PerfumeInfo> perfumeList =perfumeInfoRepo.findAll();

        Page<PerfumeInfo> perfumeInfos = null;

        if(category.equals("title")){
            perfumeInfos = perfumeInfoRepo.findByPerfumeNameContaining(pageable, keyword);
        }else if (category.equals("content")) {
            perfumeInfos = perfumeInfoRepo.findByBrandContaining(pageable, keyword);
        }else{
            perfumeInfos = perfumeInfoRepo.findAll(pageable);
        }

        model.addAttribute("perfumeList", perfumeList);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);


        return "/perfume/perfumeList";
    }

    //향수 이름으로 상세 페이지 들어감.
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
    //향수 상세페이지에서 노트 선택시 노트 이름으로 노트 상세페이지 들어감.
    @GetMapping("/note/{note}")
    public String noteName(@PathVariable("note") String name, Model model){
        AllNote note =perfumeService.getAllNote(name);
        model.addAttribute("AllNote", note);
        return "/perfume/note";
    }

    //향수 삭제
    @GetMapping("/deletePerfume")
        public String deletePerfume(PerfumeInfo perfumeinfo){
        perfumeService.DeletePerfume(perfumeinfo);
        return "redirect:/perfume/perfumeList";
    }

    //페이지 기능 없는 검색기능.
//    @GetMapping("/searchPerfume")
//    public String searchPerfume(@RequestParam("keyword") String keyword,
//                                @RequestParam("category") String category, Model model
//                                ){
//        System.out.println("=====================");
//
//        List<PerfumeInfo> search = null;
//        if(category.equals("perfumeName")){
//            search = perfumeInfoRepo.findByPerfumeNameContaining(keyword);
//        } else if (category.equals("brand")) {
//            search = perfumeInfoRepo.findByBrandContaining(keyword);
//        }else {
//             search = perfumeInfoRepo.findAll();
//        }
//
//        model.addAttribute("category", category);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("perfumeList", search);
//
//        return "/perfume/perfumeList";
//    }
}
