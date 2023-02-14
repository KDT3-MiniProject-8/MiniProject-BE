package com.example.miniprojectbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue("L")
public class Loan extends Item{

    @Column(name = "minRate")
    private BigDecimal minRate;

    @Column(name = "maxRate")
    private BigDecimal maxRate;

    @Column(name = "delay")
    private String delay;

    @Builder
    public Loan(Long itemId, String category, String bank, String itemName, String type, String join, int limit, String preference, String target, List<Basket> basketList,
                   BigDecimal minRate, BigDecimal maxRate, String mature) {
        super(itemId, category, bank, itemName,type,join,limit,preference,target,basketList); // 부모 클래스 생성자 호출
        this.minRate = minRate; // 추가된 필드
        this.maxRate = maxRate; // 추가된 필드
        this.delay = mature; // 추가된 필드
    }
}
