package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/api/logout")
    public HashMap<String, String> logout(@RequestHeader(name = "Authorization") String header) {
        return logoutService.saveHeaderTokenToBlackList(header);
    }
}
