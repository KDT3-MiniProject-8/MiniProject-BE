package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.DepositResponseDTO;
import com.example.miniprojectbe.dto.LoanResponseDTO;
import org.springframework.data.domain.Slice;


public interface SearchListService {
    Slice<DepositResponseDTO> pagingByDeposit(String category, int page);
    Slice<LoanResponseDTO> pagingByLoan(String category, int page);
}
