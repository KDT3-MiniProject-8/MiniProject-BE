package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.MemberInfoResponseDTO;
import com.example.miniprojectbe.dto.MemberRequestDTO;
import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.dto.MemberUpdateRequestDTO;
import com.example.miniprojectbe.entity.Member;

import java.util.HashMap;

public interface MemberService {

    HashMap<String, String> memberSignup(MemberRequestDTO memberRequestDTO);
    HashMap<String, String> login(MemberLoginDTO memberLoginDTO);
    HashMap<String, String> saveHeaderTokenToBlackList(String header);
    boolean checkEmailDuplicate(String memberId);
    String findPassword(String memberId, String name);
    MemberInfoResponseDTO findMemberInfoByMemberId(String memberId);
    HashMap<String, String> updateMemberInfo(String header, MemberUpdateRequestDTO memberUpdateRequestDTO);
    HashMap<String, Object> getMemberInfo(String header);
}
