package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositResponseDTO;
import com.example.miniprojectbe.dto.SearchListRequestDTO;
import com.example.miniprojectbe.dto.LoanResponseDTO;
import com.example.miniprojectbe.service.SearchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchListController {

    private final SearchListService searchListService;

    @GetMapping("/depositList")
    public List<DepositResponseDTO> depositList(){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("정기예금");
        return searchListService.findByDeposit(input);
    }
    @GetMapping("/savingsList")
    public List<DepositResponseDTO> savingsList(){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("적금");
        return searchListService.findByDeposit(input);
    }
    @GetMapping("/mortgageLoan")
    public List<LoanResponseDTO> mortgageLoan(){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("주택담보대출");
        return searchListService.findByLoan(input);
    }
    @GetMapping("/charterLoan")
    public List<LoanResponseDTO> charterLoan(){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("전세자금대출");
        return searchListService.findByLoan(input);
    }
}
