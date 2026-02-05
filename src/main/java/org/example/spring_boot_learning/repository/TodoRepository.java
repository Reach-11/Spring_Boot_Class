package org.example.spring_boot_learning.repository;

import org.example.spring_boot_learning.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
}
