package com.example.todolist;

/*
목적:
이 파일은 스프링 부트 애플리케이션의 진입점입니다. 애플리케이션을 시작하는 부트스트랩 클래스 역할을 합니다.

기능:
@SpringBootApplication 어노테이션이 적용된 클래스로, 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성을
모두 자동으로 설정합니다. 이 클래스에서 main 메소드를 실행하면 내장 톰캣 서버가 시작되고, 애플리케이션이 실행됩니다.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

}
