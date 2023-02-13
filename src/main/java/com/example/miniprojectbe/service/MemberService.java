package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.MemberLoginDTO;

import java.util.HashMap;

public interface MemberService {

    HashMap<String, String> login(MemberLoginDTO memberLoginDTO);
}
