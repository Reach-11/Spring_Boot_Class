package org.example.spring_boot_learning.service;

import org.example.spring_boot_learning.model.TodoModel;
import org.example.spring_boot_learning.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public TodoModel createTodo(TodoModel todo) {
        return todoRepository.save(todo);
    }
    public List<TodoModel> findAll() {
        return todoRepository.findAll();
    }
    public TodoModel findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Todo not found with id: " + id));
    }
    public TodoModel update(Long id, TodoModel updatedTodo) {
        TodoModel existingTodo = findById(id);
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setCompleted(updatedTodo.isCompleted());
        return todoRepository.save(existingTodo);
    }
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
