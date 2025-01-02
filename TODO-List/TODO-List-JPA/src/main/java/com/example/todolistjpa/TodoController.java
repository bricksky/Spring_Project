package com.example.todolistjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestParam(required = false) Boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        if (completed != null) {
            todo.setCompleted(completed);
        }
        todoRepository.save(todo);
        return todo;
    }
}
