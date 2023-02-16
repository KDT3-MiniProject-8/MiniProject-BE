package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;
import com.example.miniprojectbe.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/searchDeposit")
    public Slice<DepositProductDTO> searchDeposit(@RequestParam String content,@RequestParam int page){
        return productSearchService.searchDeposit(content, page);

    }

    @GetMapping("/searchLoan")
    public Slice<LoanProductDTO> searchLoan(@RequestParam String content,@RequestParam int page){
        return productSearchService.searchLoan(content, page);

    }
}
