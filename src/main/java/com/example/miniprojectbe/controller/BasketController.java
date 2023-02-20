package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;

    // 상품 장바구니 담기
    @PostMapping("/wish")
    public HashMap<String, String> addCart(@RequestHeader(name = "Authorization") String header, Long itemId) {
        return basketService.addCart(header, itemId);
    }

    // 장바구니 조회 (예적금)
    @GetMapping("/wish_list/deposit")
    public HashMap<String, Object> getDepositCartList(@RequestHeader(name = "Authorization") String header, @RequestParam int page) {
        return basketService.getDepositCartList(header, page);
    }

    // 장바구니 조회 (대출)
    @GetMapping("/wish_list/loan")
    public HashMap<String, Object> getLoanCartList(@RequestHeader(name = "Authorization") String header, @RequestParam int page) {
        return basketService.getLoanCartList(header, page);
    }

    // 장바구니 상품 삭제 (1개)
    @DeleteMapping("/wish/delete/{basketId}")
    public HashMap<String, String> deleteCartByBasketId(@PathVariable Long basketId) {
        return basketService.deleteCartByBasketId(basketId);
    }

    // 장바구니 비우기 (나의 장바구니에 담긴 상품 전체 삭제)
    @DeleteMapping("/wish/delete_all")
    public HashMap<String, String> deleteAllCartsByHeader(@RequestHeader(name = "Authorization") String header) {
        return basketService.deleteAllCartsByHeader(header);
    }
}
