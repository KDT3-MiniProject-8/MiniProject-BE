package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.MemberInfoResponseDTO;
import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.service.MemberService;
import com.example.miniprojectbe.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class RecommendController {

    private final MemberService memberService;
    private final RecommendService recommendService;
    private final JwtProvider jwtProvider;
    @GetMapping("/mainRecommend")
    public HashMap<String, Object> getRecommendList(@RequestHeader(name = "Authorization") String header) {
        MemberLoginDTO memberLoginDTO = jwtProvider.getMemberDTO(header);
        MemberInfoResponseDTO findMember = memberService.findMemberInfoByMemberId(memberLoginDTO.getMemberId());
        String bank = findMember.getBank();
        String category = findMember.getCategory();

        if (category.equals("정기예금") || category.equals("적금")) {
            return recommendService.recommendDepositList(bank, category);
        }
        else {
            return recommendService.recommendLoanList(bank,category);
        }
    }

    @GetMapping("/customRecommend")
    public HashMap<String, Object> getCustomRecommendList(@RequestHeader(name = "Authorization") String header, @RequestParam(name = "page") int page) {
        MemberLoginDTO memberLoginDTO = jwtProvider.getMemberDTO(header);
        MemberInfoResponseDTO findMember = memberService.findMemberInfoByMemberId(memberLoginDTO.getMemberId());
        String bank = findMember.getBank();
        String category = findMember.getCategory();
        String preference = findMember.getDistrict();
        String target = findMember.getJob();

        if (category.equals("정기예금") || category.equals("적금")) {
            return recommendService.recommendCustomDepositList(bank, category, preference, target, page);
        }
        else {
            return recommendService.recommendCustomLoanList(bank, category, preference, target, page);
        }
    }
}
