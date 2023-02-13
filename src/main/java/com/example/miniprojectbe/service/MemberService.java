package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.MemberRequestDTO;
import com.example.miniprojectbe.dto.MemberLoginDTO;

import java.util.HashMap;

public interface MemberService {

    String memberSignup(MemberRequestDTO memberRequestDTO);
    HashMap<String, String> login(MemberLoginDTO memberLoginDTO);
    HashMap<String, String> saveHeaderTokenToBlackList(String header);
    boolean checkEmailDuplicate(String memberId);
}
