package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.DepositDetailDTO;
import com.example.miniprojectbe.dto.LoanDetailDTO;
import com.example.miniprojectbe.entity.Deposit;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Loan;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.*;
import com.example.miniprojectbe.service.SelectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelectDetailServiceImpl implements SelectDetailService {
    private final DepositRepository depositRepository;
    private final LoanRepository loanRepository;
    private final BasketRepository basketRepository;
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @Override
    public LoanDetailDTO selectDetailLoan(Long itemId, String header) {
        String memberId = jwtProvider.getMemberIdByHeader(header);
        Member member = memberRepository.findByMemberId(memberId).get();
        Item item = itemRepository.findByItemId(itemId).get();

        Loan result = loanRepository.findByItemId(itemId).orElse(null);
        Boolean basket = basketRepository.existsByMemberAndItem(member,item);


        return new LoanDetailDTO(result, basket);

    }

    @Override
    public DepositDetailDTO selectDetailDeposit(Long itemId, String header) {
        String memberId = jwtProvider.getMemberIdByHeader(header);
        Member member = memberRepository.findByMemberId(memberId).get();
        Item item = itemRepository.findByItemId(itemId).get();

        Deposit result = depositRepository.findByItemId(itemId).orElse(null);
        Boolean basket = basketRepository.existsByMemberAndItem(member,item);

        return  new DepositDetailDTO(result, basket);
    }
}
