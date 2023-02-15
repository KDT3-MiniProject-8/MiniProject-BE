package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Deposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositProductDTO {

    private String bank;
    private String itemName;
    private String type;
    private String rate;
    private String prefRate;

    public DepositProductDTO(Deposit deposit){
        this.bank=deposit.getBank();
        this.itemName=deposit.getItemName();
        this.type=deposit.getType();
        this.rate=String.valueOf(deposit.getRate());
        this.prefRate=String.valueOf(deposit.getPrefRate());
    }
}
