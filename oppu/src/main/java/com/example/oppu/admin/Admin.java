package com.example.oppu.admin;

import com.example.oppu.base_entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Admin extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //관리자 아이디
    @Column(length = 40, nullable = false)
    private String adminId;

    //관리자 비밀번호
    @Column(length = 60, nullable = false)
    private String  password;

    //관리자 닉네임
    @Column(nullable = false, unique = true)
    private String nickname;

    //관리자 이메일
    @Column(nullable = false, unique = true)
    private String email;


}
