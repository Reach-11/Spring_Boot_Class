package org.example.spring_boot_learning.service;

import org.example.spring_boot_learning.exception.NotFoundException;
import org.example.spring_boot_learning.model.TodoModel;
import org.example.spring_boot_learning.repository.TodoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.DataFormatException;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public TodoModel createTodo(TodoModel todo) {
        try {
            return todoRepository.save(todo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Todo title must be unique", e);

        }
    }
    public List<TodoModel> findAll() {
        return todoRepository.findAll();
    }
    public TodoModel findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Todo not found with id: " + id));
    }
    public TodoModel update(Long id, TodoModel updatedTodo) {
        TodoModel existingTodo = findById(id);
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setCompleted(updatedTodo.isCompleted());
        try{
            return todoRepository.save(existingTodo);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Todo title must be unique",e);
        }

    }
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
