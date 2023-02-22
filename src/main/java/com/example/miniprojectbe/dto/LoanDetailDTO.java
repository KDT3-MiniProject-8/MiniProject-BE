package com.example.miniprojectbe.dto;


import com.example.miniprojectbe.entity.Loan;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class LoanDetailDTO {

    private Long itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;
    private String join;
    private Integer limit;
    private String preference;
    private String target;
    private BigDecimal minRate;
    private BigDecimal maxRate;
    private String delay;
    private Boolean basket;

    @Builder
    public LoanDetailDTO(Long itemId, String category, String bank, String itemName, String type, String join, int limit, String preference, String target, BigDecimal minRate, BigDecimal maxRate, String delay) {
        this.itemId = itemId;
        this.category = category;
        this.bank = bank;
        this.itemName = itemName;
        this.type = type;
        this.join = join;
        this.limit = limit;
        this.preference = preference;
        this.target = target;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.delay = delay;
    }


    public LoanDetailDTO(Loan loan, Boolean basket) {
        this.itemId = loan.getItemId();
        this.category = loan.getCategory();
        this.bank = loan.getBank();
        this.itemName = loan.getItemName();
        this.type = loan.getType();
        this.join = loan.getJoin();
        this.limit = loan.getLimit();
        this.preference = loan.getPreference();
        this.target = loan.getTarget();
        this.minRate = loan.getMinRate();
        this.maxRate = loan.getMaxRate();
        this.delay = loan.getDelay();
        this.basket = basket;
    }
}
