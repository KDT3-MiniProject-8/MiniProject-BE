package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositDetailDTO;
import com.example.miniprojectbe.dto.LoanDetailDTO;
import com.example.miniprojectbe.entity.Deposit;
import com.example.miniprojectbe.entity.Loan;
import com.example.miniprojectbe.repository.DepositRepository;
import com.example.miniprojectbe.repository.LoanRepository;
import com.example.miniprojectbe.service.SelectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelectDetailServiceImpl implements SelectDetailService {
    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;


    @Override
    public LoanDetailDTO selectDetailLoan(Long itemId) {

        Loan result = loanRepository.findByItemId(itemId).orElse(null);

        return new LoanDetailDTO(result);

    }

    @Override
    public DepositDetailDTO selectDetailDeposit(Long itemId) {

        Deposit result = depositRepository.findByItemId(itemId).orElse(null);

        return  new DepositDetailDTO(result);
    }
}
