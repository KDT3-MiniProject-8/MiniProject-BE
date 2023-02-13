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
@DiscriminatorValue("D")
public class Deposit extends Item{

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "prefRate")
    private BigDecimal prefRate;

    @Column(name = "mature")
    private String mature;
}
