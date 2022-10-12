package com.example.oppu.member;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MemberService {

    //회원가입
    Member insertMember(String username, String nickname, String password, String email);

    Member getMember(String username);

    Page<Member> getList(String searchCate, String searchKeyword, Pageable pageable);
}
