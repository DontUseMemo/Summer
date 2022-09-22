package com.example.oppu.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    //관리자 아이디 이메일 데이터 가져오기
    @Query(value = "SELECT A FROM Admin A WHERE A.email = :email or A.adminId = :adminId")
    Admin findAdminByEmailOradminId(String email, String adminId);
}
