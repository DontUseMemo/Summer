package com.example.oppu.member;

import com.example.oppu.base_entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //회원가입 id
    private String username;

    //회원 닉네임
    @Column(unique = true)
    private String nickname;

    //회원 비밀번호
    private String password;

    //회원 email
    @Column(unique = true)
    private String email;

    //회원 경고 횟수
    private int accrueReportNumber;

    //회원 최근 로그인
    private LocalDateTime recentLoginDate;

    //계정 정지 상태 YN (BaseTimeEntity로 옮길 예정.)
    private String stopYN;
}
