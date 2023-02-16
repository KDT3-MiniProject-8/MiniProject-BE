package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Deposit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class DepositDetailDTO {

    private Long itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;
    private String join;
    private Integer limit;
    private String preference;
    private String target;
    private BigDecimal rate;
    private BigDecimal prefRate;
    private String mature;

    @Builder
    public DepositDetailDTO(Long itemId, String category, String bank, String itemName, String type, String join, int limit, String preference, String target, BigDecimal rate, BigDecimal prefRate, String mature) {
        this.itemId = itemId;
        this.category = category;
        this.bank = bank;
        this.itemName = itemName;
        this.type = type;
        this.join = join;
        this.limit = limit;
        this.preference = preference;
        this.target = target;
        this.rate = rate;
        this.prefRate = prefRate;
        this.mature = mature;
    }

    public DepositDetailDTO(Deposit deposit) {
        this.itemId = deposit.getItemId();
        this.category = deposit.getCategory();
        this.bank = deposit.getBank();
        this.itemName = deposit.getItemName();
        this.type = deposit.getType();
        this.join = deposit.getJoin();
        this.limit = deposit.getLimit();
        this.preference = deposit.getPreference();
        this.target = deposit.getTarget();
        this.rate = deposit.getRate();
        this.prefRate = deposit.getPrefRate();
        this.mature = deposit.getMature();
    }


}
