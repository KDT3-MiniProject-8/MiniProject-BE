package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;

import java.util.List;

public interface ProductSearchService {

    List<DepositProductDTO> searchDeposit(String content);
    List<LoanProductDTO> searchLoan(String content);
}
