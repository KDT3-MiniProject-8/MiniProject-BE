package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Deposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositResponseDTO {

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
    private String mature;

    public DepositResponseDTO(Deposit deposit) {
        this.category = deposit.getCategory();
        this.bank = deposit.getBank();
        this.itemName = deposit.getItemName();
        this.type = deposit.getType();
        this.join = deposit.getJoin();
        this.limit = String.valueOf(deposit.getLimit());
        this.preference = deposit.getPreference();
        this.target = deposit.getTarget();
        this.rate = String.valueOf(deposit.getRate());
        this.prefRate = String.valueOf(deposit.getPrefRate());
        this.mature = deposit.getMature();
    }
}
