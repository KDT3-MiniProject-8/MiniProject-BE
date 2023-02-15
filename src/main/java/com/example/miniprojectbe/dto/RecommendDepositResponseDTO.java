package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Deposit;
import com.example.miniprojectbe.entity.Item;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecommendDepositResponseDTO {

    private Long itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;
    private BigDecimal rate;
    private BigDecimal prefRate;

    public RecommendDepositResponseDTO(Deposit deposit) {
        this.itemId = deposit.getItemId();
        this.category = deposit.getCategory();
        this.bank = deposit.getBank();
        this.itemName = deposit.getItemName();
        this.type = deposit.getType();
        this.rate = deposit.getRate();
        this.prefRate = deposit.getPrefRate();
    }

}
