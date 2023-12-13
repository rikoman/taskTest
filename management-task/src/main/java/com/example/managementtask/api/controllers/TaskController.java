package com.example.managementtask.api.controllers;

import com.example.managementtask.api.services.TaskServiceImpl;
import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;
    private final MappingResponse<Task> response;

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO dto, Authentication authentication){
        return response.entity(taskService.createTask(dto,authentication));
    }

    @GetMapping()
    public ResponseEntity<List<Task>> readAllTask(){
        return response.listEntity(taskService.readAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> readTaskById(@PathVariable Long id){
        return response.entity(taskService.readTaskById(id));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Task>> readAllTaskByAuthor(Authentication authentication){
        return response.listEntity(taskService.readTaskByAuthor(authentication));
    }

    @GetMapping("/author/{name}")
    public ResponseEntity<List<Task>> readAllTaskByAuthorName(@PathVariable String name){
        return response.listEntity(taskService.readTaskByAuthorName(name));
    }

    @GetMapping("/executor")
    public ResponseEntity<List<Task>> readAllTaskByExecutor(Authentication authentication){
        return response.listEntity(taskService.readTaskByExecutor(authentication));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updatePartInfoTask(@PathVariable Long id,@RequestBody TaskDTO dto,Authentication authentication){
        return response.entity(taskService.updatePartInfoTask(id,dto,authentication));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return HttpStatus.OK;
    }
}