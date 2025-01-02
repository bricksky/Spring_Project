package com.example.todolist;

/*
목적:
이 클래스는 웹 요청을 처리합니다. 클라이언트(예: 웹 브라우저)로부터의 요청을 받아 적절한 비즈니스 로직을 처리하고,
결과를 다시 클라이언트에게 전달하는 역할을 합니다.

기능:
@RestController 어노테이션이나 @Controller 어노테이션을 사용하여 컨트롤러로 정의됩니다.
여기에는 HTTP 요청을 받는 여러 메소드가 있을 수 있으며, 각 메소드는 특정 URL 경로에 대응되어 웹 요청을 처리합니다.
예를 들어, 할 일 목록을 반환하거나, 새로운 할 일을 추가하는 요청을 처리합니다.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;  // TodoRepository를 사용하기 위해 주입

    // TODO 항목 추가
    @PostMapping("/add")
    public Todo addTodo(@RequestParam String task){
        Todo todo = new Todo();
        todo.setTask(task); // todo 저장
        todo.setCompleted(false);   // 새로 추가된 TODO 진행상황은 미진행(false)상태로 지정
        todoRepository.save(todo);   // 데이터베이스에 저장
        return todo;    // 추가된 TODO 항목 반환
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestParam(required = false) Boolean completed){
        Todo todo = todoRepository.findById(id).orElseThrow();
        if(completed != null){
            todo.setCompleted(completed);
        }
        todoRepository.save(todo);
        return todo;
    }


    // 모든 TODO 항목 조회
    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }
}
