package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositResponseDTO;
import com.example.miniprojectbe.dto.SearchListRequestDTO;
import com.example.miniprojectbe.dto.LoanResponseDTO;
import com.example.miniprojectbe.repository.DepositRepository;
import com.example.miniprojectbe.repository.LoanRepository;
import com.example.miniprojectbe.service.SearchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchListServiceImpl implements SearchListService {

    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;

    @Override
    public List<DepositResponseDTO> findByDeposit(SearchListRequestDTO searchListRequestDTO) {
        return depositRepository.findAllByCategory(searchListRequestDTO.toDepositEntity().getCategory())
                .stream().map(DepositResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanResponseDTO> findByLoan(SearchListRequestDTO searchListRequestDTO) {
        return loanRepository.findAllByCategory(searchListRequestDTO.toLoanEntity().getCategory())
                .stream().map(LoanResponseDTO::new)
                .collect(Collectors.toList());
    }
}
