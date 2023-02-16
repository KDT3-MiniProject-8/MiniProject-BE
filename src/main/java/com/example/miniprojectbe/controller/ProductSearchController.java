package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;
import com.example.miniprojectbe.dto.ProductRequestDTO;
import com.example.miniprojectbe.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/searchDeposit")
    public Slice<DepositProductDTO> searchDeposit(@RequestParam ProductRequestDTO productRequestDTO){
        return productSearchService.searchDeposit(productRequestDTO.getContent(), productRequestDTO.getPage());

    }

    @GetMapping("/searchLoan")
    public Slice<LoanProductDTO> searchLoan(@RequestParam ProductRequestDTO productRequestDTO){
        return productSearchService.searchLoan(productRequestDTO.getContent(), productRequestDTO.getPage());

    }
}
