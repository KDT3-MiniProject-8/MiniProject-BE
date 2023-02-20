package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositResponseDTO;
import com.example.miniprojectbe.dto.SearchListRequestDTO;
import com.example.miniprojectbe.dto.LoanResponseDTO;
import com.example.miniprojectbe.service.SearchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SearchListController {

    private final SearchListService searchListService;

    @GetMapping("/deposit_list")
    public Slice<DepositResponseDTO> depositList(@RequestParam int page){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("정기예금");
        return searchListService.pagingByDeposit(input.getCategory(), page);
    }
    @GetMapping("/savings_list")
    public Slice<DepositResponseDTO> savingsList(@RequestParam int page){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("적금");
        return searchListService.pagingByDeposit(input.getCategory(), page);
    }
    @GetMapping("/mortgage_loan")
    public Slice<LoanResponseDTO> mortgageLoan(@RequestParam int page){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("주택담보대출");
        return searchListService.pagingByLoan(input.getCategory(),page);
    }
    @GetMapping("/charter_loan")
    public Slice<LoanResponseDTO> charterLoan(@RequestParam int page){
        SearchListRequestDTO input = new SearchListRequestDTO();
        input.setCategory("전세자금대출");
        return searchListService.pagingByLoan(input.getCategory(),page);
    }
}
