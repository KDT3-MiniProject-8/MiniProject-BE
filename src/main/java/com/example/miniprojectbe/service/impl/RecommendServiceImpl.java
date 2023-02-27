package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.RecommendDepositResponseDTO;
import com.example.miniprojectbe.dto.RecommendLoanResponseDTO;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.DepositRepository;
import com.example.miniprojectbe.repository.ItemRepository;
import com.example.miniprojectbe.repository.LoanRepository;
import com.example.miniprojectbe.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {
    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;

    @Override
    public HashMap<String, Object> recommendDepositList(String bank, String category) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            List<RecommendDepositResponseDTO> recommendDepositResponseDTOS = depositRepository.findTop3ByBankAndCategoryOrderByPrefRateDesc(bank, category)
                    .stream()
                    .map(RecommendDepositResponseDTO::new)
                    .collect(Collectors.toList());

            result.put("resultCode", "success");
            result.put("resultData", recommendDepositResponseDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }

        return result;
    }

    @Override
    public HashMap<String, Object> recommendLoanList(String bank, String category) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            List<RecommendLoanResponseDTO> recommendLoanResponseDTOS = loanRepository.findTop3ByBankAndCategoryOrderByMinRateAsc(bank, category)
                    .stream()
                    .map(RecommendLoanResponseDTO::new)
                    .collect(Collectors.toList());

            result.put("resultCode", "success");
            result.put("resultData", recommendLoanResponseDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }

        return result;
    }

    @Override
    public HashMap<String, Object> recommendCustomDepositList(String bank, String category, String preference, String target, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        HashMap<String, Object> result = new HashMap<>();

        try {
            Slice<RecommendDepositResponseDTO> recommendDepositResponseDTOS
                    = depositRepository.findByBankOrCategoryOrPreferenceOrTargetOrderByPrefRateDesc(bank, category, preference, target, pageRequest)
                                        .map(RecommendDepositResponseDTO::new);

            result.put("resultCode", "success");
            result.put("resultData", recommendDepositResponseDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }

        return result;
    }

    @Override
    public HashMap<String, Object> recommendCustomLoanList(String bank, String category, String preference, String target, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        HashMap<String, Object> result = new HashMap<>();

        try {
            Slice<RecommendLoanResponseDTO> recommendLoanResponseDTOS
                    = loanRepository.findByBankOrCategoryOrPreferenceOrTargetOrderByMinRateAsc(bank, category, preference, target, pageRequest)
                                    .map(RecommendLoanResponseDTO::new);

            result.put("resultCode", "success");
            result.put("resultData", recommendLoanResponseDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }

        return result;
    }
}
