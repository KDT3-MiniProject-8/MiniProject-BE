package com.example.miniprojectbe.service;

import java.util.HashMap;

public interface RecommendService {

    HashMap<String, Object> recommendDepositList(String bank, String category);
    HashMap<String, Object> recommendLoanList(String bank, String category);
    HashMap<String, Object> recommendCustomDepositList(String bank, String category, String preference, String target, int page);
    HashMap<String, Object> recommendCustomLoanList(String bank, String category, String preference, String target, int page);

}
