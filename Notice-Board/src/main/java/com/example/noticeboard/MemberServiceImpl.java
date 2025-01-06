package com.example.noticeboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member saveEntity(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public boolean login(LoginDto loginDto) {
        Member member = findByUserName(loginDto.getUsername());
        return member != null && member.getPassword().equals(loginDto.getPassword());
    }

    @Override
    public Member findByUserName(String username) {
        return memberRepository.findByUserName(username);
    }

    @Override
    public Member save(@Valid MemberDto memberDto) {
        // MemberDto를 Member엔티티로 변환하고 저장
        Member member = Member.builder()
                .userName(memberDto.getUsername())
                .password(memberDto.getPassword())
                .build();
        return saveEntity(member);
    }
}