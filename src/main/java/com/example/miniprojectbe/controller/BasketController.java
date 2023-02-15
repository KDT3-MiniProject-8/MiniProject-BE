package com.example.miniprojectbe.controller;

import com.example.miniprojectbe.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    // 상품 장바구니 담기
    @PostMapping("/api/cart")
    public HashMap<String, String> addCart(@RequestHeader(name = "Authorization") String header, Long itemId) {
        return basketService.addCart(header, itemId);
    }

    // 장바구니 조회
    @GetMapping("/api/cartList")
    public HashMap<String, Object> getCartList(@RequestHeader(name = "Authorization") String header) {
        return basketService.getCartList(header);
    }
}
