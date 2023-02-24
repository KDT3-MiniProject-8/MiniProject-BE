package com.example.miniprojectbe.service;

import java.util.HashMap;

public interface PurchaseService {

    HashMap<String, String> addPurchase(String header, Long itemId);
    HashMap<String, Object> getDepositPurchaseList(String header);
    HashMap<String, Object> getLoanPurchaseList(String header);
    HashMap<String, String> deletePurchaseByPurchaseId(Long purchaseId);
    HashMap<String, Object> countPurchase(String header);
}
