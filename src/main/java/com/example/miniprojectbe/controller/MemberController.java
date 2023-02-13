package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.MemberRequestDTO;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/signup")
    public String signup(MemberRequestDTO memberRequestDTO){
        return memberService.memberSignup(memberRequestDTO);
    }

}
