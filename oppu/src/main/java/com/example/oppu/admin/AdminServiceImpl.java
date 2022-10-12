package com.example.oppu.admin;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

   private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //관리자목록 메소드
    @Override
    public List<Admin> getAdminList() {
        return (List<Admin>) adminRepository.findAll();
    }

    //관리자가입 메소드
    @Override
    public void insertAdmin(Admin admin) {
        admin.setCreateDate(LocalDateTime.now());
        admin.setUpdateDate(LocalDateTime.now());
        adminRepository.save(admin);
    }

    //관리자상세 메소드
    @Override
    public Admin getAdmin(Admin admin) {
        return adminRepository.findById(admin.getId()).get();
    }

    //관리자수정 메소드
    @Override
    public void updateAdmin(Admin admin) {
        Admin findAdmin = adminRepository.findById(admin.getId()).get();
        findAdmin.setAdminId(admin.getAdminId());
        findAdmin.setEmail(admin.getEmail());
        findAdmin.setPassword(admin.getPassword());
        findAdmin.setNickname(admin.getNickname());

        adminRepository.save(findAdmin);
    }

    //관리자삭제 메소드
    @Override
    public void deleteAdmin(Admin admin) {
        adminRepository.deleteById(admin.getId());
    }

    //관리자검색 메소드
    @Override
    public Admin getAdminWhereIdOrEmail(String email, String adminId) {
        return adminRepository.findAdminByEmailOradminId(email, adminId);
    }

//    @Override
//    public List<Admin> getAdminListEncodingByAdminList(List<Admin> adminList) {
//        return null;
//    }
}
