package com.example.miniprojectbe.service;

import java.util.HashMap;

public interface BasketService {

    HashMap<String, String> addCart(String header, Long itemId);
    HashMap<String, Object> getCartList(String header);
    HashMap<String, String> deleteCartByBasketId(Long basketId);
    HashMap<String, String> deleteAllCartsByHeader(String header);
}
