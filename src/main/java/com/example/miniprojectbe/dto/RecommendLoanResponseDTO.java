package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Deposit;
import com.example.miniprojectbe.entity.Loan;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecommendLoanResponseDTO {

    private Long itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;
    private BigDecimal minRate;
    private BigDecimal maxRate;

    public RecommendLoanResponseDTO (Loan loan) {
        this.itemId = loan.getItemId();
        this.category = loan.getCategory();
        this.bank = loan.getBank();
        this.itemName = loan.getItemName();
        this.type = loan.getType();
        this.minRate = loan.getMinRate();
        this.maxRate = loan.getMaxRate();
    }

}
