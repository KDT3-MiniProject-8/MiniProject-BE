package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositResponseDTO;
import com.example.miniprojectbe.dto.LoanResponseDTO;
import com.example.miniprojectbe.repository.DepositRepository;
import com.example.miniprojectbe.repository.LoanRepository;
import com.example.miniprojectbe.service.SearchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchListServiceImpl implements SearchListService {

    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;

    @Override
    public Slice<LoanResponseDTO> pagingByLoan(String category, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        return loanRepository.findAllByCategory(category, pageRequest)
                .map(LoanResponseDTO::new);
    }

    @Override
    public Slice<DepositResponseDTO> pagingByDeposit(String category, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        return depositRepository.findAllByCategory(category, pageRequest)
                .map(DepositResponseDTO::new);

    }
}
