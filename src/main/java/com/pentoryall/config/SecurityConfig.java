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

/* 시큐리티 설정 활성화 및 bean 등록 */
@Component
@EnableWebSecurity
public class SecurityConfig {


    /* 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 정적리소스의 대한 요청을 제외하겠다는 설정 static 파일 하위
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /* Http 요청에 대한 설정을 SecurityFilterChain에 설정 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                /* CSRF 공격 방지는 기본적으로 활성화 되어 있어 비활성화 처리 */
                .csrf(csrf -> csrf.disable())
                /* 요청에 대한 권한 체크 */
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/board/**", "/thumbnail/**", "/user/update", "/user/delete").hasAnyRole("USER", "ADMIN");
                    /* 위에 서술 된 패턴 외의 요청은 인증 되지 않은 사용자도 요청 허가 */
                    auth.anyRequest().permitAll();
                })
                /* 로그인 설정 */
                .formLogin(login -> {
                    /* 로그인 페이지 설정 */
                    login.loginPage("/views/user/login");
                    /* 성공 시 랜딩 페이지 설정 */
                    login.defaultSuccessUrl("/");
                    /* 로그인 실패 시 랜딩 페이지 설정 */
                    login.failureForwardUrl("/views/user/loginfail");
                    /* 파라미터명 변경 */
                    login.usernameParameter("userId");
                    login.passwordParameter("password");
                })
                /* 로그아웃 설정 */
                .logout(logout -> {
                    /* 로그아웃 요청 URL */
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/views/user/logout"));
                    /* JSESSIONID 쿠키 삭제 */
                    logout.deleteCookies("JSESSIONID");
                    /* 세션 만료 */
                    logout.invalidateHttpSession(true);
                    /* 로그아웃 후 랜딩 페이지 */
                    logout.logoutSuccessUrl("/");
                })
                .build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        /* 자동 로그인 설정 S */
//        http.rememberMe(c -> {
//            c.rememberMeParameter("autoLogin") // 자동 로그인으로 사용할 요청 파리미터 명, 기본값은 remember-me
//                    .tokenValiditySeconds(60 * 60 * 24 * 30) // 로그인을 유지할 기간(30일로 설정), 기본값은 14일
//                    .userDetailsService(memberInfoService) // 재로그인을 하기 위해서 인증을 위한 필요 UserDetailsService 구현 객체
//                    .authenticationSuccessHandler(new LoginSuccessHandler()); // 자동 로그인 성공시 처리 Handler
//
//        });
//        /* 자동 로그인 설정 E */
//
//        return http.build();
//    }

}
