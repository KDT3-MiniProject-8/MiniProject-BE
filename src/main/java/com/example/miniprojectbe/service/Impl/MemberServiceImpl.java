package com.example.miniprojectbe.service.Impl;

import com.example.miniprojectbe.dto.MemberRequestDTO;
import com.example.miniprojectbe.repository.MemberRepository;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String memberSignup(MemberRequestDTO memberRequestDTO) {
        try {
            memberRepository.save(memberRequestDTO.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }


}
