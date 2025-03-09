package com.bilbobegins1997.TaskFlow.controller;


import com.bilbobegins1997.TaskFlow.entity.Task;
import com.bilbobegins1997.TaskFlow.entity.Todo;
import com.bilbobegins1997.TaskFlow.repository.TaskRepository;
import com.bilbobegins1997.TaskFlow.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("user/todo")
public class TasksController {

    final TaskRepository taskRepository;
    final TodoRepository todoRepository;


    public TasksController(TaskRepository taskRepository, TodoRepository todoRepository) {
        this.taskRepository = taskRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("task")
    public List<Task> getAllTodo(){
        return taskRepository.findAll();
    }

    @GetMapping("task/{id}")
    public Task getTodoById(@PathVariable Long id){
        return taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException(" no such ID,"+ id));
    }

    @PostMapping("{todoId}/task")
    public ResponseEntity<Task> saveTodo(@PathVariable Long todoId, @RequestBody Task task){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("no todo with ID, " + todoId));
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        task.setTodo(todo);
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @DeleteMapping("task/{id}")
    public void deleteTodo(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("task/{id}")
    public void patchbody(@RequestBody Task task,@PathVariable Long id){
     Task temp =  taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no "+id));
        temp.setBody(task.getBody());
        temp.setTitle(task.getTitle());
        taskRepository.save(temp);
    }









}
