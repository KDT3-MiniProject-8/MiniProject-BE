package com.example.miniprojectbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@DiscriminatorValue("L")
public class Loan extends Item{

    @Column(name = "minRate")
    private BigDecimal rate;

    @Column(name = "maxRate")
    private BigDecimal prefRate;

    @Column(name = "delay")
    private String delay;
}
