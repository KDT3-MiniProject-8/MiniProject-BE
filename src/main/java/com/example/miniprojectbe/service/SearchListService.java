package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.DepositResponseDTO;
import com.example.miniprojectbe.dto.SearchListRequestDTO;
import com.example.miniprojectbe.dto.LoanResponseDTO;

import java.util.List;

public interface SearchListService {
    List<DepositResponseDTO> findByDeposit(SearchListRequestDTO searchListRequestDTO);
    List<LoanResponseDTO> findByLoan(SearchListRequestDTO searchListRequestDTO);
}
