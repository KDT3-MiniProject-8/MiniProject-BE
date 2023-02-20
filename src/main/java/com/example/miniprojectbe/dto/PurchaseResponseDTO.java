package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Purchase;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseResponseDTO {

    private Long purchaseId;
    private Long itemId;
    private String category;
    private String bank;
    private String itemName;
    private String type;
    private LocalDateTime purchase_date;
    private String status;

    public PurchaseResponseDTO(Purchase purchase) {
        this.purchaseId = purchase.getPurchaseId();
        this.itemId = purchase.getItem().getItemId();
        this.category = purchase.getItem().getCategory();
        this.bank = purchase.getItem().getBank();
        this.itemName = purchase.getItem().getItemName();
        this.type = purchase.getItem().getType();
        this.purchase_date = purchase.getPurchase_date();
        this.status = purchase.getStatus();
    }
}
