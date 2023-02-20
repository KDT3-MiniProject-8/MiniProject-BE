package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositProductDTO;
import com.example.miniprojectbe.dto.LoanProductDTO;
import com.example.miniprojectbe.repository.DepositRepository;
import com.example.miniprojectbe.repository.LoanRepository;
import com.example.miniprojectbe.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;

    @Override
    public Slice<DepositProductDTO> searchDeposit(String content, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        return depositRepository.findAllByBankContainingOrItemNameContaining(content, content, pageRequest)
                .map(DepositProductDTO::new);
    }

    @Override
    public Slice<LoanProductDTO> searchLoan(String content, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        return loanRepository.findAllByBankContainingOrItemNameContaining(content, content, pageRequest)
                .map(LoanProductDTO::new);

    }
}
