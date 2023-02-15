package com.example.miniprojectbe.service;

import com.example.miniprojectbe.entity.Item;

import java.util.Optional;

public interface ItemService {
    Item findItemByItemId(Long itemId);
}
