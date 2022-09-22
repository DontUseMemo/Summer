package com.example.oppu.admin;

import java.util.List;

public interface AdminService {

    //관리자목록 메소드
    List<Admin> getAdminList();

    //관리자가입 메소드
    void insertAdmin(Admin admin);

    //관리자상세 메소드
    Admin getAdmin(Admin admin);

    //관리자수정 메소드
    void updateAdmin(Admin admin);

    //관리자삭제 메소드
    void deleteAdmin(Admin admin);

    //관리자검색 메소드
    Admin getAdminWhereIdOrEmail(String email, String adminId);

    //암호화`
//    List<Admin> getAdminListEncodingByAdminList(List<Admin> adminList);
}
