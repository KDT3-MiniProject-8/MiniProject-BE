package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.repository.ItemRepository;
import com.example.miniprojectbe.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item findItemByItemId(Long itemId) {
        try {
            Item findItem = itemRepository.findByItemId(itemId).get();
            return findItem;
        } catch (NoSuchElementException e) {
            log.error("해당 itemId와 일치하는 상품이 없습니다.");
            return null;
        }
    }
}
