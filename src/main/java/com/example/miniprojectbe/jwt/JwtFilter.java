package com.example.miniprojectbe.jwt;

import com.example.miniprojectbe.dto.MemberLoginDTO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Builder
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public static JwtFilter of(JwtProvider jwtProvider) {
        return JwtFilter.builder()
                .jwtProvider(jwtProvider)
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(header);

        try {
            MemberLoginDTO memberLoginDTO = jwtProvider.getMemberDTO(header);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(memberLoginDTO, null, memberLoginDTO.getAuthorities()));

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("토큰이 존재하지 않습니다.(3)");
            filterChain.doFilter(request, response);
        }
    }
}
