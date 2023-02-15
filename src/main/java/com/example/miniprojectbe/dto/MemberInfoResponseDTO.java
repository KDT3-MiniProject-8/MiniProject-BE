package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberInfoResponseDTO {

    private String memberId;
    private String name;
    private String birth;
    private String job;
    private String district;
    private String bank;
    private String category;

    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .name(name)
                .birth(LocalDateTime.parse(birth))
                .job(job)
                .district(district)
                .bank(bank)
                .category(category)
                .build();
    }

    public MemberInfoResponseDTO(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.birth = String.valueOf(member.getBirth());
        this.job = member.getJob();
        this.district = member.getDistrict();
        this.bank = member.getBank();
        this.category = member.getCategory();
    }
}
