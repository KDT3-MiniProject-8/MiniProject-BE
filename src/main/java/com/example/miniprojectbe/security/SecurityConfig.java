package com.example.miniprojectbe.security;

import com.example.miniprojectbe.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private static final String[] PUBLIC_URLS = { // 이 URL은 권한검사 하지 않음
            "/signup", "/login", "/", "/find_password", "/find_password/send_mail",
            "/deposit_list", "/savings_list","/mortgage_loan", "/charter_loan", "/duplication/{memberId}"

    };

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .cors()
                .and()
                .authorizeRequests() //다음 리퀘스트에 대한 사용권한 체크
                .mvcMatchers(PUBLIC_URLS).permitAll() //회원가입 메인페이지 로그인 주소는 누구나 접근 가능
                .and()
                .authorizeRequests()
                .anyRequest().authenticated() //나머지 요청들은 모두 인증된 회원만 접근 가능하도록
                .and()
                .csrf().disable() //rest api 이므로 csrf 보안 필요없음
                .httpBasic().disable() //기본설정 사용안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt token 인증 방식으로 세션 필요없음 생성안함
                .and()
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                ).build(); //인증 처리 기본필터 외에 별도의 인증로직 가진 필터를 생성하고 사용하고 싶을 때
    }

    //Cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().mvcMatchers(PUBLIC_URLS));
    }

}
