package com.example.oppu.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    // 닉네임 검색 페이징
    Page<Member> findByUsernameContaining(String username, Pageable pageable);

    // 이메일 검색 페이징
    Page<Member> findByEmailContaining(String email, Pageable pageable);

}
