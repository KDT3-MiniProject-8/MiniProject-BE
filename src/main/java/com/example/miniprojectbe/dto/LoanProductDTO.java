package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoanProductDTO {

    private String itemId;
    private String bank;
    private String itemName;
    private String type;
    private String minRate;
    private String maxRate;

    public LoanProductDTO(Loan loan){
        this.itemId=String.valueOf(loan.getItemId());
        this.bank=loan.getBank();
        this.itemName=loan.getItemName();
        this.type=loan.getType();
        this.minRate=String.valueOf(loan.getMinRate());
        this.maxRate=String.valueOf(loan.getMaxRate());
    }
}
