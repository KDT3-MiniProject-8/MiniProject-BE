package com.example.miniprojectbe.service;

import java.util.HashMap;

public interface PurchaseService {

    HashMap<String, String> addPurchase(String header, Long itemId);
    HashMap<String, Object> getDepositPurchaseList(String header, int page);
    HashMap<String, Object> getLoanPurchaseList(String header, int page);
    HashMap<String, String> deletePurchaseByPurchaseId(Long purchaseId);
}
