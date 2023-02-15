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
public class MemberUpdateRequestDTO {

    private String password;
    private String name;
    private String birth;
    private String job;
    private String district;
    private String bank;
    private String category;

    public Member toEntity(){
        return Member.builder()
                .password(password)
                .name(name)
                .birth(LocalDateTime.parse(birth))
                .job(job)
                .district(district)
                .bank(bank)
                .category(category)
                .build();
    }

}
