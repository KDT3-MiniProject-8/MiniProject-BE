package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    // 상품 구매(신청)
    @PostMapping("/purchase")
    public HashMap<String, String> addPurchase(@RequestHeader(name = "Authorization") String header, Long itemId) {
        return purchaseService.addPurchase(header, itemId);
    }

    // 구매(신청) 조회 (예적금)
    @GetMapping("/deposit/purchase_list")
    public HashMap<String, Object> getDepositPurchaseList(@RequestHeader(name = "Authorization") String header, @RequestParam int page) {
        return purchaseService.getDepositPurchaseList(header, page);
    }

    // 구매(신청) 조회 (대출)
    @GetMapping("/loan/purchase_list")
    public HashMap<String, Object> getLoanPurchaseList(@RequestHeader(name = "Authorization") String header, @RequestParam int page) {
        return purchaseService.getLoanPurchaseList(header, page);
    }

    // 상품 취소(삭제) (1개)
    @PutMapping("/delete/purchase/{purchaseId}")
    public HashMap<String, String> deletePurchaseByPurchaseId(@PathVariable Long purchaseId) {
        return purchaseService.deletePurchaseByPurchaseId(purchaseId);
    }

}
