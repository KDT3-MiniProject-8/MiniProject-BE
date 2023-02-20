package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.PurchaseResponseDTO;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.entity.Purchase;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.ItemRepository;
import com.example.miniprojectbe.repository.MemberRepository;
import com.example.miniprojectbe.repository.PurchaseRepository;
import com.example.miniprojectbe.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final JwtProvider jwtProvider;

    @Override
    public HashMap<String, String> addPurchase(String header, Long itemId) {
        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            Member member = memberRepository.findByMemberId(memberId).get();
            Item item = itemRepository.findByItemId(itemId).get();

            if (purchaseRepository.existsByMemberAndItem(member, item)) {
                HashMap<String, String> result = new HashMap<>();
                result.put("resultCode", "duplicate");
                return result;
            } else {
                Purchase purchase = Purchase.builder()
                        .member(member)
                        .item(item)
                        .purchase_date(LocalDateTime.now())
                        .status("신청완료")
                        .build();
                purchaseRepository.save(purchase);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return returnFailed();
        }
        return returnSuccess();
    }

    @Override
    public HashMap<String, Object> getDepositPurchaseList(String header, int page) {
        List<String> category = new ArrayList<>();
        category.add("정기예금");
        category.add("적금");
        return getPurchaseList(header, category, page);
    }

    @Override
    public HashMap<String, Object> getLoanPurchaseList(String header, int page) {
        List<String> category = new ArrayList<>();
        category.add("전세자금대출");
        category.add("주택담보대출");
        return getPurchaseList(header, category, page);
    }

    private HashMap<String, Object> getPurchaseList(String header, List<String> category, int page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        HashMap<String, Object> result = new HashMap<>();

        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            Slice<PurchaseResponseDTO> purchaseResponseDTOS = purchaseRepository.findByMemberIdAndCategory(memberId, category, pageRequest)
                    .map(PurchaseResponseDTO::new);

            result.put("resultCode", "success");
            result.put("resultData", purchaseResponseDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }
        return result;
    }
    @Transactional
    @Override
    public HashMap<String, String> deletePurchaseByPurchaseId(Long purchaseId) {
        try {
            Purchase purchase =  purchaseRepository.findByPurchaseId(purchaseId).orElse(null);

            purchase.softDelete();

        } catch (Exception e) {
            e.printStackTrace();
            return returnFailed();
        }
        return returnSuccess();
    }

    private HashMap<String, String> returnFailed() {
        HashMap<String, String> result = new HashMap<>();
        result.put("resultCode", "failed");
        return result;
    }

    private HashMap<String, String> returnSuccess() {
        HashMap<String, String> result = new HashMap<>();
        result.put("resultCode", "success");
        return result;
    }
}
