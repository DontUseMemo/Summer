package com.example.oppu.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public Member insertMember(String username, String nickname, String password, String email){
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        member.setNickname(nickname);
        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);
        this.memberRepository.save(member);
        return member;
    }
}
