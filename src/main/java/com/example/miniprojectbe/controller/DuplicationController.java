package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.service.DuplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DuplicationController {

    private final DuplicationService duplicationService;

    @PostMapping("/api/duplication/{memberId}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String memberId) {
        System.out.println(memberId);
        return ResponseEntity.ok(duplicationService.checkEmailDuplicate(memberId));
    }

}
