package com.example.miniprojectbe.service;

import com.example.miniprojectbe.dto.DepositDetailDTO;
import com.example.miniprojectbe.dto.LoanDetailDTO;

public interface SelectDetailService {

    LoanDetailDTO selectDetailLoan(Long itemId, String header);

    DepositDetailDTO selectDetailDeposit(Long itemId, String header);


}
