package com.bilbobegins1997.TaskFlow.controller;

import com.bilbobegins1997.TaskFlow.entity.Todo;
import com.bilbobegins1997.TaskFlow.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("TaskFlow")
public class TodoController {

   final TodoRepository todoRepository;

    TodoController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping("all")
    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id){
        Todo todo  = todoRepository.findById(id).orElseThrow(() -> new NoSuchElementException(" no such ID,"+ id));
        return todo;
    }

    @PostMapping("")
    public Todo saveTodo(@RequestBody Todo todo){
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void patchbody(@RequestBody Todo todo,@PathVariable Long id){
        Todo todo1 =  todoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no "+id));
        todo1.setBody(todo.getBody());
        todo1.setTitle(todo.getTitle());
        todoRepository.save(todo1);
    }



}
