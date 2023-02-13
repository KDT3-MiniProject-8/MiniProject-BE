package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.MemberRepository;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    public HashMap<String, String> login(MemberLoginDTO memberLoginDTO) {
        HashMap<String, String> result = new HashMap<>();

        try {
            Member findMember = memberRepository.findByMemberId(memberLoginDTO.getMemberId()).get();
            if (isValidPassword(memberLoginDTO, findMember)) {
                String accessToken = jwtProvider.makeToken(findMember);
                result.put("resultCode", "success");
                result.put("accessToken", accessToken);

                return result;
            }
        } catch (NoSuchElementException e) {
            result.put("resultCode", "failed");
            return result;
        }

        return result;
    }

    private boolean isValidPassword(MemberLoginDTO memberLoginDTO, Member member) {
        return memberLoginDTO.getPassword().equals(member.getPassword());
    }

}
