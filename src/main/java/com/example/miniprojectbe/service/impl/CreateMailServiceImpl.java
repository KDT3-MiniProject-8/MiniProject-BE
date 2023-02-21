package com.example.miniprojectbe.service.impl;

import com.example.miniprojectbe.dto.MailDTO;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.jwt.JwtProvider;
import com.example.miniprojectbe.repository.ItemRepository;
import com.example.miniprojectbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateMailServiceImpl {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public MailDTO createMailAndChangePassword(String memberId, String name){
        String tempPassword = getTempPassword();
        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(memberId);
        mailDTO.setTitle(name+"님의 fincok 임시비밀번호 안내 이메일 입니다.");
        mailDTO.setMessage("안녕하세요. fincok 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
                + tempPassword + " 입니다.");
        updatePassword(tempPassword, memberId);
        return mailDTO;
    }

    public MailDTO createPurchaseMail(String header, Long itemId){
        try {
            String memberId = jwtProvider.getMemberIdByHeader(header);
            Member member = memberRepository.findByMemberId(memberId).get();
            Item item = itemRepository.findByItemId(itemId).get();

            String name = member.getName();
            String category = item.getCategory();
            String itemName = item.getItemName();

            MailDTO mailDTO = new MailDTO();
            mailDTO.setAddress(memberId);
            mailDTO.setTitle(name + "님의 fincok " + category + " 상품 신청 관련 안내 이메일 입니다.");
            mailDTO.setMessage("안녕하세요. fincok " + category + " 상품 신청 관련 안내 이메일 입니다.\n" + "[" + name + "]" + "님께서 신청하신 [" + itemName + "]의 신청이 완료 되었습니다.");

            return mailDTO;
        } catch (Exception e) {
            log.error("메일 생성에 실패하였습니다.");
            e.printStackTrace();
            return new MailDTO();
        }
    }

    @Transactional
    public void updatePassword(String tempPassword, String memberId){
        String updatedPassword = passwordEncoder.encode(tempPassword);
        memberRepository.updateMemberPassword(memberId,updatedPassword);
    }


    public String getTempPassword(){ //임시 비밀번호 생성
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
