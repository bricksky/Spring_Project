package com.example.todolist;

/*
목적
이 클래스는 데이터베이스의 todo 테이블에 해당하는 데이터 모델을 나타냅니다. 주로 데이터베이스와 매핑되는 객체로서,
이 클래스의 인스턴스 하나하나가 데이터베이스의 한 행(row)과 대응됩니다.

기능:
@Entity 어노테이션을 사용하여 JPA(Java Persistence API)가 관리하도록 설정됩니다.
이 클래스 안에는 Todo 객체의 속성들이 필드로 정의되어 있으며, 각 필드는 데이터베이스의 컬럼에 매핑됩니다.
예를 들어, 할 일의 제목, 설명, 완료 여부 등의 필드가 있을 수 있습니다.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//이 클래스가 데이터베이스의 테이블을 나타내는 클래스임을 JPA에게 알려주는 역할

public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 고유 번호 (예: 1, 2, 3...)
    private String task;    // 할 일의 내용 (예: "장보기")
    private boolean completed;  // 완료 여부 (true: 완료, false: 미완료)

    // 고유 번호를 가져오는 메서드
    public Long getId() {
        return id;
    }
    // 고유 번호를 설정하는 메서드
    public void setId(Long id) {
        this.id = id;
    }

    // 할 일 내용을 가져오는 메서드
    public String getTask() {
        return task;
    }
    // 할 일 내용을 설정하는 메서드
    public void setTask(String task) {
        this.task = task;
    }

    // 완료 여부를 가져오는 메서드
    public boolean isCompleted() {
        return completed;
    }
    // 완료 여부를 설정하는 메서드
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
