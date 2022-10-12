package com.example.oppu.member;

import com.example.oppu.DataNotFoundException;
import com.example.oppu.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    @Override
    public Page<Member> getList(String searchCate, String searchKeyword, Pageable pageable) {
        Page<Member> members = null;

        if (searchCate.equals("username")) {
            members = memberRepository.findByUsernameContaining(searchKeyword, pageable);
        } else if (searchCate.equals("email")) {
            members = memberRepository.findByEmailContaining(searchKeyword, pageable);
        } else {
            members = memberRepository.findAll(pageable);
        }

        return members;
    }

    @Override
    public Member updateMember(Member member) {
        this.memberRepository.save(member);
        return member;
    }

}
