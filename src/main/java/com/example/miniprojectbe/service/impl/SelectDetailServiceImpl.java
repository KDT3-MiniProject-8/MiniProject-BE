package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositDetailDTO;
import com.example.miniprojectbe.dto.LoanDetailDTO;
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

        List<LoanDetailDTO> listResult = loanRepository.findByItemId(itemId)
                .stream().map(LoanDetailDTO::new)
                .collect(Collectors.toList());

        LoanDetailDTO result = listResult.get(0);

        return  result;

    }

    @Override
    public DepositDetailDTO selectDetailDeposit(Long itemId) {

        List<DepositDetailDTO> listResult = depositRepository.findByItemId(itemId)
                .stream().map(DepositDetailDTO::new)
                .collect(Collectors.toList());

        DepositDetailDTO result = listResult.get(0);

        return  result;
    }
}
