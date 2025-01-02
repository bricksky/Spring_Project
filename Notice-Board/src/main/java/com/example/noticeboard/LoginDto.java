package com.example.noticeboard;

import lombok.Data;

@Data
public class LoginDto {
    private String username; // 로그인 아이디
    private String password; // 로그인 비밀번호
}
