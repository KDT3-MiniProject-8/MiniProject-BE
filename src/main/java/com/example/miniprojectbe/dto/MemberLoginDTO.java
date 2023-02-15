package com.example.miniprojectbe.dto;

import com.example.miniprojectbe.entity.Member;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberLoginDTO {

    private String memberId;
    private String password;
    private String bank;
    private String category;

    public MemberLoginDTO(Claims claims) {
        this.memberId = claims.get("memberId", String.class);
        this.bank = claims.get("bank", String.class);
        this.category = claims.get("category", String.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
