package com.example.miniprojectbe.service.impl;


import com.example.miniprojectbe.dto.*;
import com.example.miniprojectbe.entity.Blacklist;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.BlacklistRepository;
import com.example.miniprojectbe.repository.MemberRepository;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final BlacklistRepository blacklistRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public  HashMap<String, String> memberSignup(MemberRequestDTO memberRequestDTO) {
        HashMap<String, String> result = new HashMap<>();

        try {
            String encodedPassword = passwordEncoder.encode(memberRequestDTO.getPassword());
            memberRequestDTO.setPassword(encodedPassword);
            memberRepository.save(memberRequestDTO.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }
        result.put("resultCode", "success");
        return result;
    }

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

        result.put("resultCode", "failed");
        return result;
    }

    @Override
    public HashMap<String, String> saveHeaderTokenToBlackList(String header) {

        blacklistRepository.save(Blacklist.builder().token(header).createDate(LocalDateTime.now()).build());

        HashMap<String, String> result = new HashMap<>();
        result.put("resultCode", "success");

        return result;
    }

    @Override
    public boolean checkEmailDuplicate(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    @Override
    public String findPassword(String memberId, String name) {
        try {
            memberRepository.findByMemberIdAndName(memberId, name).get();
            return "success";
        } catch (NoSuchElementException e) {
            return "failed";
        }
    }

    @Override
    public Member findMemberByMemberId(String memberId) {
        try {
            Member findMember = memberRepository.findByMemberId(memberId).get();
            return findMember;
        } catch (NoSuchElementException e) {
            log.error("해당 memberId와 일치하는 회원이 없습니다.");
            return null;
        }
    }

    @Transactional
    @Override
    public HashMap<String, String> updateMemberInfo(String header, MemberUpdateRequestDTO memberUpdateRequestDTO) {

        HashMap<String, String> result = new HashMap<>();

        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            Member member = findMemberByMemberId(memberId);

            memberUpdateRequestDTO.setPassword(passwordEncoder.encode(memberUpdateRequestDTO.getPassword()));
            member.updateMember(memberUpdateRequestDTO);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }
        result.put("resultCode", "success");
        return result;
    }

    @Override
    public HashMap<String, Object> getMemberInfo(String header) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            Member member = findMemberByMemberId(memberId);
            if (member!=null) {
                MemberInfoResponseDTO findMember = new MemberInfoResponseDTO(member);
                result.put("resultCode", "success");
                result.put("resultData", findMember);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }
        result.put("resultCode", "failed");
        return result;
    }

    private boolean isValidPassword(MemberLoginDTO memberLoginDTO, Member findMember) {
        return passwordEncoder.matches(memberLoginDTO.getPassword(), findMember.getPassword());
    }

}
