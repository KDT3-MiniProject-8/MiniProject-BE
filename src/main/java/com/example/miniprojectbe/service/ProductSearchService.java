package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;
import org.springframework.data.domain.Slice;

public interface ProductSearchService {
    Slice<DepositProductDTO> searchDeposit(String category, int page);
    Slice<LoanProductDTO> searchLoan(String category, int page);
}
