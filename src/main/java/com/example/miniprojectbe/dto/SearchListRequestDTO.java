package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Deposit;
import com.example.miniprojectbe.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchListRequestDTO {

    private String category;

    public Deposit toDepositEntity(){
        return Deposit.builder()
                .category(category)
                .build();
    }

    public Loan toLoanEntity(){
        return Loan.builder()
                .category(category)
                .build();
    }
}
