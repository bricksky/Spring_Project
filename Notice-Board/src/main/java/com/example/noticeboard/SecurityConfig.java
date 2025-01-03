package com.example.noticeboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/join", "/createMember").permitAll()  // 인증 없이 접근 가능
                .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                .and()
                .formLogin()
                .loginPage("/login")  // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/", true)  // 로그인 성공 후 리다이렉트할 페이지
                .permitAll()  // 모든 사용자가 로그인 페이지 접근 허용
                .and()
                .logout()
                .permitAll();  // 로그아웃 허용
    }
}
