package com.example.noticeboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; // 데이터 소스 주입

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 인코더 빈 등록
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
                .passwordEncoder(passwordEncoder()); // 데이터베이스 인증에 비밀번호 인코더 사용
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/join", "/createMember").permitAll()  // 인증 없이 접근 가능한 경로
                .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                .and()
                .formLogin()
                .loginPage("/login")  // 사용자 정의 로그인 페이지 지정
                .defaultSuccessUrl("/", true)  // 로그인 성공 후 리다이렉트할 기본 URL
                .permitAll()  // 모든 사용자가 로그인 페이지 접근 허용
                .and()
                .logout()
                .permitAll();  // 로그아웃 허용
    }
}
