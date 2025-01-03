package com.example.noticeboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member saveEntity(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member saveDto(MemberDto memberDto) {
        // String encodedPassword = bCryptPasswordEncoder.encode(memberDto.getPassword());
        Member member = Member.builder()
                .userName(memberDto.getUsername())
                .password(memberDto.getPassword())
                .build();
        return saveEntity(member);
    }

    @Override
    public Member findByUserName(String username){
        return memberRepository.findByUserName(username);
    }

    public boolean login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        Member byUsername = memberRepository.findByUserName(username);

        if(byUsername != null && byUsername.getPassword().equals(loginDto.getPassword())) {
            return true; // 평문 비밀번호 비교
        }
        return false;
    }

    @Override
    public void save(MemberDto memberDto) {
    saveDto(memberDto);
    }
}