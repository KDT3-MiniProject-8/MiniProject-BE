package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Basket;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasketResponseDTO {

    private Long basketId;
    private Long itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;

    public BasketResponseDTO(Basket basket) {
        this.basketId = basket.getBasket();
        this.itemId = basket.getItem().getItemId();
        this.category = basket.getItem().getCategory();
        this.bank = basket.getItem().getBank();
        this.itemName = basket.getItem().getItemName();
        this.type = basket.getItem().getType();
    }
}
