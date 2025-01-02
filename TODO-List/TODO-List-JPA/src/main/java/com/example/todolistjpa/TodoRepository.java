package com.example.todolistjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
                                         // JpaRepository<엔티티타입, @Id타입>
}
