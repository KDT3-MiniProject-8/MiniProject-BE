package com.example.miniprojectbe.service;

import com.example.miniprojectbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DuplicationServiceImpl implements DuplicationService{

    private  final UserRepository userRepository;
    @Override
    public boolean checkEmailDuplicate(String memberId) {
        System.out.println("memberId"+memberId);
        System.out.println(userRepository.existsByMemberId(memberId));
        return userRepository.existsByMemberId(memberId);

    }
}
