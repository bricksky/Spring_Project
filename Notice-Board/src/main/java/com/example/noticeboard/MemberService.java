package com.example.noticeboard;

public interface MemberService {

    Member saveEntity(Member member);
    Member saveDto(MemberDto memberDto);
    Member findByUserName(String username);

    boolean login(LoginDto loginDto);
    void save(MemberDto memberDto);
}
