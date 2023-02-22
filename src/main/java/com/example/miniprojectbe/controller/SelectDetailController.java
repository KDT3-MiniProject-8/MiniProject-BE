package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositDetailDTO;
import com.example.miniprojectbe.dto.LoanDetailDTO;
import com.example.miniprojectbe.service.impl.SelectDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SelectDetailController {

    private final SelectDetailServiceImpl selectDetailService;

    @GetMapping("/search/deposit/detail/{itemId}")
    public DepositDetailDTO searchDeposit(@PathVariable Long itemId, @RequestHeader(name = "Authorization")String header){

        return selectDetailService.selectDetailDeposit(itemId, header);
    }

    @GetMapping("/search/loan/detail/{itemId}")
    public LoanDetailDTO searchLoan(@PathVariable Long itemId, @RequestHeader(name = "Authorization")String header){

        return selectDetailService.selectDetailLoan(itemId,header);
    }
}
