package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;
import com.example.miniprojectbe.repository.DepositRepository;
import com.example.miniprojectbe.repository.LoanRepository;
import com.example.miniprojectbe.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;

    @Override
    public List<DepositProductDTO> searchDeposit(String content) {
        return depositRepository.findAllByBankContainingOrItemNameContaining(content, content)
                .stream().map(DepositProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanProductDTO> searchLoan(String content) {
        return loanRepository.findAllByBankContainingOrItemNameContaining(content, content)
                .stream().map(LoanProductDTO::new)
                .collect(Collectors.toList());

    }
}
