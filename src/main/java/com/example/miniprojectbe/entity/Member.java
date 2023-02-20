package com.example.miniprojectbe.entity;

import com.example.miniprojectbe.dto.MemberUpdateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "memberId", nullable = false)
    private String memberId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth", nullable = false)
    private LocalDateTime birth;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "bank", nullable = false)
    private String bank;

    @Column(name = "category", nullable = false)
    private String category;

    @OneToMany(mappedBy = "member")
    private List<Basket> basketList;

    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        password = memberUpdateRequestDTO.getPassword();
        name = memberUpdateRequestDTO.getName();
        birth = LocalDateTime.parse(memberUpdateRequestDTO.getBirth());
        job = memberUpdateRequestDTO.getJob();
        district = memberUpdateRequestDTO.getDistrict();
        bank = memberUpdateRequestDTO.getBank();
        category = memberUpdateRequestDTO.getCategory();
    }
}
