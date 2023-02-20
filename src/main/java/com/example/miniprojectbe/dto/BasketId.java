package com.example.miniprojectbe.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class BasketId implements Serializable {

    private String member;
    private Long item;
}
