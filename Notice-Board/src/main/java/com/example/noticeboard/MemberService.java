package com.example.noticeboard;

import javax.validation.Valid;

public interface MemberService {

    Member saveEntity(Member member); // 엔티티 저장

    boolean login(LoginDto loginDto); // 로그인 처리

    Member findByUserName(String username); // 사용자 검색

    Member save(@Valid MemberDto memberDto); // MemberDto 저장
}
