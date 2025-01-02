package com.example.todolistjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/add")
    public Todo addTodo(@RequestParam String task){
        Todo todo = new Todo();
        todo.setDate(LocalDate.EPOCH);
        todo.setTask(task);
        todo.setCompleted(false);
        todoRepository.save(todo);
        return todo;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }
}
