package org.example.spring_boot_learning.controller;

import org.example.spring_boot_learning.model.TodoModel;
import org.example.spring_boot_learning.service.TodoService;
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
    public List<TodoModel> findAll() {
         return todoService.findAll();
   }
   @PostMapping
    public TodoModel createTodo(@RequestBody TodoModel todo) {
         return todoService.createTodo(todo);
   }
   @PutMapping("/{id}")
    public TodoModel updateTodo(@PathVariable Long id,@RequestBody TodoModel todo) {
         return todoService.update(id, todo);
   }
   @GetMapping("/{id}")
    public TodoModel findById(@PathVariable Long id) {
         return todoService.findById(id);
   }
   @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
         todoService.delete(id);}
}
