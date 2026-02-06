package org.example.spring_boot_learning.controller;

import jakarta.validation.Valid;
import org.example.spring_boot_learning.dto.TodoCreateRequest;
import org.example.spring_boot_learning.dto.TodoResponse;
import org.example.spring_boot_learning.dto.TodoUpdateRequest;
import org.example.spring_boot_learning.model.TodoModel;
import org.example.spring_boot_learning.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
   private final TodoService todoService;
   public TodoController(TodoService todoService) {
         this.todoService = todoService;
   }
   @GetMapping
    public List<TodoResponse> findAll() {
         return todoService.findAll().stream().
               map(todo -> new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted())).toList()
                 ;//map use for convert List<TodoModel> to List<TodoResponse>
   }
   @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoCreateRequest request) {
            TodoModel saved = todoService.createTodo(new TodoModel(request.title(), request.completed()));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new TodoResponse(saved.getId(), saved.getTitle(), saved.isCompleted()));
   }
   @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable Long id,@Valid@RequestBody TodoUpdateRequest request) {
         TodoModel updated = todoService.update(id, new TodoModel(request.title(), request.completed()));
         return new TodoResponse(updated.getId(), updated.getTitle(), updated.isCompleted());
   }
   @GetMapping("/{id}")
    public TodoResponse findById(@PathVariable Long id) {
         TodoModel t= todoService.findById(id);
         return new TodoResponse(t.getId(), t.getTitle(), t.isCompleted());
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
       todoService.delete(id);
         return ResponseEntity.noContent().build();
   }
}
