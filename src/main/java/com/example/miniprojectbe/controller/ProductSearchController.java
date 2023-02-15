package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;
import com.example.miniprojectbe.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/searchDeposit")
    public List<DepositProductDTO> searchDeposit(@RequestParam String content){
        return productSearchService.searchDeposit(content);

    }

    @GetMapping("/searchLoan")
    public List<LoanProductDTO> searchLoan(@RequestParam String content){
        return productSearchService.searchLoan(content);

    }
}
