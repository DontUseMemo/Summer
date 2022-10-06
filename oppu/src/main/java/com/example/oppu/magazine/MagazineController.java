package com.example.oppu.magazine;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/magazine")
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    //매거진 페이징
    @GetMapping("/magazineList")
    public String getMagazineList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Magazine> paging = this.magazineService.getMagazineList(page);
        model.addAttribute("paging", paging);
        return "/magazine/magazineList";
    }

    //매거진 작성
    @GetMapping("/insertMagazine")
    public String insertMagazine() {
        return "/magazine/insertMagazine";
    }

    //매거진 작성 데이터전달
    @PostMapping("/insertMagazine")
    public String insertMagazine(Magazine magazine) {
        magazineService.insertMagazine(magazine);
        return "redirect:/magazine/magazineList";
    }

    //매거진 상세
    @GetMapping("/getMagazine")
    public String getMagazine(Magazine magazine, Model model) {
        model.addAttribute("magazine", magazineService.getMagazine(magazine));
        return "/magazine/getMagazine";
    }

    //매거진 수정
    @PostMapping("/updateMagazine")
    public String updateMagazine(Magazine magazine) {
        magazineService.updateMagazine(magazine);
        return "redirect:/magazine/magazineList";
    }

    //매거진 삭제
    @GetMapping("/deleteMagazine")
    public String deleteMagazine(Magazine magazine) {
        magazineService.deleteMagazine(magazine);
        return "redirect:/magazine/magazineList";
    }

    //매거진 검색
    @GetMapping("/selectMagazine")
    public String selectMagazine() {
        return "/magazine/selectMagazine";
    }

    //매거진 검색 데이터전달
    @PostMapping("/selectMagazine")
    public String resultMagazine(Magazine magazine, Model model) {
        model.addAttribute("admin", magazineService.getMagazineWhereTitleOrWriterOrContent(magazine.getTitle(), magazine.getWriter(), magazine.getContent()));
        return "/magazine/resultMagazine";
    }
}