package com.pentoryall.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    /* TODO : 우선 모든 요청을 허용하게 설정, 추후 변경 */
                    auth.anyRequest().permitAll();
                })
                .formLogin(login -> {
                    // TODO : 로그인 설정
                    // /* 로그인 페이지 설정 */
                    // login.loginPage("/member/login");
                    // /* 성공 시 랜딩 페이지 설정 */
                    // login.defaultSuccessUrl("/");
                    // /* 로그인 실패 시 랜딩 페이지 설정 */
                    // login.failureForwardUrl("/member/loginfail");
                    // /* 파라미터명 변경 */
                    // login.usernameParameter("memberId");
                    // login.passwordParameter("memberPwd");
                })
                .logout(logout -> {
                    // TODO : 로그아웃 설정
                    /* 로그아웃 요청 URL */
                    // logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"));
                    // /* JSESSIONID 쿠키 삭제 */
                    // logout.deleteCookies("JSESSIONID");
                    // /* 세션 만료 */
                    // logout.invalidateHttpSession(true);
                    // /* 로그아웃 후 랜딩 페이지 */
                    // logout.logoutSuccessUrl("/");
                })
                .build();
    }
}
