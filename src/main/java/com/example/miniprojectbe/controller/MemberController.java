package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public HashMap<String, String> login(MemberLoginDTO memberLoginDTO) {
        return memberService.login(memberLoginDTO);
    }
}
