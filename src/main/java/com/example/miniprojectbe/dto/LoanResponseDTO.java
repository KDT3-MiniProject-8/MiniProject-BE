package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponseDTO {

    private String itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;
    private String join;
    private String limit;
    private String preference;
    private String target;
    private String minRate;
    private String maxRate;
    private String delay;

    public LoanResponseDTO(Loan loan) {
        this.itemId=String.valueOf(loan.getItemId());
        this.category = loan.getCategory();
        this.bank = loan.getBank();
        this.itemName = loan.getItemName();
        this.type = loan.getType();
        this.join = loan.getJoin();
        this.limit = String.valueOf(loan.getLimit());
        this.preference = loan.getPreference();
        this.target = loan.getTarget();
        this.minRate = String.valueOf(loan.getMinRate());
        this.maxRate = String.valueOf(loan.getMaxRate());
        this.delay = loan.getDelay();
    }
}
