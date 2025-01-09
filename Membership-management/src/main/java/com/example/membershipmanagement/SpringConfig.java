package com.example.membershipmanagement;

import com.example.membershipmanagement.repository.MemberRepository;
import com.example.membershipmanagement.repository.MemoryMemberRepository;
import com.example.membershipmanagement.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
