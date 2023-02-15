package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.BasketResponseDTO;
import com.example.miniprojectbe.entity.Basket;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.BasketRepository;
import com.example.miniprojectbe.service.BasketService;
import com.example.miniprojectbe.service.ItemService;
import com.example.miniprojectbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final MemberService memberService;
    private final ItemService itemService;
    private final JwtProvider jwtProvider;

    @Override
    public HashMap<String, String> addCart(String header, Long itemId) {
        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);

            Member member = memberService.findMemberByMemberId(memberId);
            Item item = itemService.findItemByItemId(itemId);

            Basket basket = Basket.builder().member(member).item(item).build();
            basketRepository.save(basket);

        } catch (Exception e) {
            e.printStackTrace();
            return returnFailed();
        }
        return returnSuccess();
    }

    @Override
    public HashMap<String, Object> getCartList(String header) {

        HashMap<String, Object> result = new HashMap<>();

        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            List<BasketResponseDTO> basketResponseDTOS = basketRepository.findByMember_MemberId(memberId)
                    .stream()
                    .map(BasketResponseDTO::new)
                    .collect(Collectors.toList());

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
            basketRepository.deleteAllByMember_MemberId(memberId);

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
