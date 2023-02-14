package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.MailDTO;
import com.example.miniprojectbe.dto.MemberRequestDTO;
import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.service.MemberService;
import com.example.miniprojectbe.service.impl.CreateMailAndUpdatePwServiceImpl;
import com.example.miniprojectbe.service.impl.SendMailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SendMailServiceImpl sendMailServiceImpl;
    private final CreateMailAndUpdatePwServiceImpl createMailAndUpdatePwServiceImpl;

    @PostMapping("/signup")
    public HashMap<String, String> signup(MemberRequestDTO memberRequestDTO){
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

    @GetMapping("/findPw")
    public HashMap<String, String> findPassword(@RequestParam(name = "memberId") String memberId,
                                                @RequestParam(name = "name") String name) {
        HashMap<String, String>result= new HashMap<>();
        String resultCode = memberService.findPassword(memberId, name);
        result.put("resultCode", resultCode);
        return result;
    }

    @PostMapping("/findPw/sendMail")
    public HashMap<String, String> sendMail(@RequestParam(name = "memberId") String memberId,
                                            @RequestParam(name = "name") String name) {
        HashMap<String, String> result = new HashMap<>();

        MailDTO mailDTO = createMailAndUpdatePwServiceImpl.createMailAndChangePassword(memberId, name);

        try {
            sendMailServiceImpl.sendMail(mailDTO);
            result.put("resultCode", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
        }

        return result;
    }
}
