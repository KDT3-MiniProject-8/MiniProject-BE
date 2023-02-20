package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.BasketResponseDTO;
import com.example.miniprojectbe.entity.Basket;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.BasketRepository;
import com.example.miniprojectbe.repository.ItemRepository;
import com.example.miniprojectbe.repository.MemberRepository;
import com.example.miniprojectbe.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final JwtProvider jwtProvider;

    @Override
    public HashMap<String, String> addCart(String header, Long itemId) {
        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);

            Member member = memberRepository.findByMemberId(memberId).get();
            Item item = itemRepository.findByItemId(itemId).get();

            if (basketRepository.existsByMemberAndItem(member, item)) {
                HashMap<String, String> result = new HashMap<>();
                result.put("resultCode", "duplicate");
                return result;
            } else {
                Basket basket = Basket.builder().member(member).item(item).build();
                basketRepository.save(basket);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return returnFailed();
        }
        return returnSuccess();
    }

    @Override
    public HashMap<String, Object> getDepositCartList(String header, int page) {
        List<String> category = new ArrayList<>();
        category.add("정기예금");
        category.add("적금");
        return getCartList(header, category, page);
    }

    @Override
    public HashMap<String, Object> getLoanCartList(String header, int page) {
        List<String> category = new ArrayList<>();
        category.add("전세자금대출");
        category.add("주택담보대출");
        return getCartList(header, category, page);
    }

    private HashMap<String, Object> getCartList(String header, List<String> category, int page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        HashMap<String, Object> result = new HashMap<>();

        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            Slice<BasketResponseDTO> basketResponseDTOS = basketRepository.findByMemberIdAndCategory(memberId, category, pageRequest)
                    .map(BasketResponseDTO::new);

            result.put("resultCode", "success");
            result.put("resultData", basketResponseDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }
        return result;
    }

    @Override
    public HashMap<String, String> deleteCartByBasketId(Long basketId) {
        try {
            Basket basket =  basketRepository.findByBasket(basketId).get();
            basketRepository.delete(basket);
        } catch (Exception e) {
            e.printStackTrace();
            return returnFailed();
        }
        return returnSuccess();
    }

    @Transactional
    @Override
    public HashMap<String, String> deleteAllCartsByHeader(String header) {
        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            basketRepository.deleteByMemberId(memberId);

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
