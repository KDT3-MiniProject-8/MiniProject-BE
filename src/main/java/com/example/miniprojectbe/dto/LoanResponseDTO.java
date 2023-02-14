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

    private String category;
    private String bank;
    private String itemName;
    private String type;
    private String join;
    private String limit;
    private String preference;
    private String target;
    private String rate;
    private String prefRate;
    private String delay;

    public LoanResponseDTO(Loan loan) {
        this.category = loan.getCategory();
        this.bank = loan.getBank();
        this.itemName = loan.getItemName();
        this.type = loan.getType();
        this.join = loan.getJoin();
        this.limit = String.valueOf(loan.getLimit());
        this.preference = loan.getPreference();
        this.target = loan.getTarget();
        this.rate = String.valueOf(loan.getRate());
        this.prefRate = String.valueOf(loan.getPrefRate());
        this.delay = loan.getDelay();
    }
}
