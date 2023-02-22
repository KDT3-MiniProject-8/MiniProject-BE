package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.dto.MailDTO;
import com.example.miniprojectbe.service.PurchaseService;
import com.example.miniprojectbe.service.impl.CreateMailServiceImpl;
import com.example.miniprojectbe.service.impl.SendMailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final SendMailServiceImpl sendMailServiceImpl;
    private final CreateMailServiceImpl createMailServiceImpl;

    // 상품 구매(신청)
    @PostMapping("/purchase")
    public HashMap<String, String> addPurchase(@RequestHeader(name = "Authorization") String header, Long itemId) {
        return purchaseService.addPurchase(header, itemId);
    }

    @PostMapping("/purchase/send_mail")
    public String sendPurchaseMail(@RequestHeader(name = "Authorization") String header, Long itemId) {
        MailDTO purchaseMail = createMailServiceImpl.createPurchaseMail(header, itemId);
        try {
            if (purchaseMail != null) {
                sendMailServiceImpl.sendMail(purchaseMail);
                return "success";
            }
        } catch (Exception e) {
            log.error("메일 전송에 실패하였습니다.");
            e.printStackTrace();
            return "failed";
        }

        return "failed";
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
    // 구매(신청)한 상품 갯수
    @GetMapping("/count_purchase")
    public HashMap<String, Object> countPurchase(@RequestHeader(name = "Authorization") String header){
        return purchaseService.countPurchase(header);
    }

}
