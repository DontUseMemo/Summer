package com.example.oppu.member;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/member")
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @GetMapping("/signUp")
    public String signup(UserInsertForm userInsertForm) {
        return "/member/signUp";
    }

    //회원가입
    @PostMapping("/signUp")
    public String signup(@Valid UserInsertForm userInsertForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("실패했음");
            return "/member/signUp";
        }
        if (!userInsertForm.getPassword1().equals(userInsertForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
            return "/member/signUp";
        }
        try {
            memberService.insertMember(userInsertForm.getUsername(), userInsertForm.getNickname(), userInsertForm.getPassword1(), userInsertForm.getEmail());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "/member/signUp";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
        }
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    // 마이페이지
    @GetMapping("/myPage")
    public String myPage(Model model, Member member) {
        model.addAttribute("member", member);
        return "/member/myPage";
    }
};