package com.example.oppu.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //관리자 목록
    @GetMapping("/getAdminList")
    public String getAdminList(Model model) {
     model.addAttribute("adminList", adminService.getAdminList());
     return "/admin/getAdminList";
    }

    //관리자 가입페이지
    @GetMapping("/insertAdmin")
    public String insertAdmin() {
        return "/admin/insertAdmin";
    }

    //관리자 가입 데이터전달
    @PostMapping("/insertAdmin")
    public String insertAdmin(Admin admin) {
        adminService.insertAdmin(admin);
        return "redirect:/admin/getAdminList";
    }

    //관리자 상세보기
    @GetMapping("/getAdmin")
    public String getMagazine(Admin admin, Model model) {
        model.addAttribute("admin", adminService.getAdmin(admin));
        return "/admin/getAdmin";
    }

    //관리자 수정
    @PostMapping("/updateAdmin")
    public String updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);
        return "redirect:/admin/getAdminList";
    }

    //관리자 삭제
    @GetMapping("/deleteAdmin")
    public String deleteAdmin(Admin admin) {
        adminService.deleteAdmin(admin);
        return "redirect:/admin/getAdminList";
    }

    //관리자 검색
    @GetMapping("/selectAdmin")
    public String selectAdmin() {
        return "/admin/selectAdmin";
    }

    //관리자 검색 데이터전달
    @PostMapping("/selectAdmin")
    public String resultAdmin(Admin admin, Model model) {
        model.addAttribute("admin", adminService.getAdminWhereIdOrEmail(admin.getEmail(), admin.getAdminId()));
        return "/admin/resultAdmin";
    }
}