package com.example.miniprojectbe.jwt;

import com.example.miniprojectbe.dto.MemberLoginDTO;
import com.example.miniprojectbe.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider { //토큰 생성 및 검증 객체

    private final JwtProperties jwtProperties;

    public String makeToken(Member member) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더 타입을 지정, jwt 사용
                .setIssuer(jwtProperties.getIssuer()) // 토큰 발급자 설정 (fincok@gmail.com으로 해둠)
                .setIssuedAt(now) // 발급 시간 설정
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(60).toMillis())) //만료시간 설정
                .claim("memberId", member.getMemberId()) //비공개 클레임 설정
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

    }

    public MemberLoginDTO getMemberDTO(String header) {
        System.out.println(header);
        validationHeader(header);
        String token = "";
        Claims claims = null;

        try {
            token = extractToken(header);
            claims = tokenToMember(token);
            return new MemberLoginDTO(claims);
        } catch (Exception e) {
            log.error("토큰이 존재하지 않습니다.(2)");
        }

        return null;
    }

    public Claims tokenToMember(String token) { //토큰 값을 claims로 바꿔줘서 리턴
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

    }

    private void validationHeader(String header) { //헤더값이 올바른지 검증
        if (header == null || !header.startsWith(jwtProperties.getTokenPrefix())) {
            log.error("토큰이 존재하지 않습니다.(1)");
        }
    }

    private String extractToken(String header) { //Bearer 떼어내는 메서드
        return header.substring(jwtProperties.getTokenPrefix().length());
    }
}
