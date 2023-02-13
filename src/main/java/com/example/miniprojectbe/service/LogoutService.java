package com.example.miniprojectbe.service;

import com.example.miniprojectbe.entity.Blacklist;
import com.example.miniprojectbe.repository.BlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final BlacklistRepository blacklistRepository;

    public HashMap<String, String> saveHeaderTokenToBlackList(String header) {

        blacklistRepository.save(Blacklist.builder().token(header).createDate(LocalDateTime.now()).build());

        HashMap<String, String> result = new HashMap<>();
        result.put("resultCode", "success");

        return result;
    }
}
