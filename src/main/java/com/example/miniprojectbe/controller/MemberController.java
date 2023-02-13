package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.MemberRequestDTO;
import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(MemberRequestDTO memberRequestDTO){
        return memberService.memberSignup(memberRequestDTO);
    }

    @PostMapping("/login")
    public HashMap<String, String> login(MemberLoginDTO memberLoginDTO) {
        return memberService.login(memberLoginDTO);
    }

    @PostMapping("/logout")
    public HashMap<String, String> logout(@RequestHeader(name = "Authorization") String header) {
        return memberService.saveHeaderTokenToBlackList(header);
    }

    @PostMapping("/api/duplication/{memberId}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String memberId) {
        return ResponseEntity.ok(memberService.checkEmailDuplicate(memberId));
    }

}
