package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositDetailDTO;
import com.example.miniprojectbe.dto.LoanDetailDTO;
import com.example.miniprojectbe.service.impl.SelectDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SelectDetailController {

    private final SelectDetailServiceImpl selectDetailService;

    @GetMapping("/searchDepositDetail/{itemId}")
    public DepositDetailDTO searchDeposit(@PathVariable Long itemId){

        return selectDetailService.selectDetailDeposit(itemId);
    }

    @GetMapping("/searchLoanDetail/{itemId}")
    public LoanDetailDTO searchLoan(@PathVariable Long itemId){

        return selectDetailService.selectDetailLoan(itemId);
    }




}
