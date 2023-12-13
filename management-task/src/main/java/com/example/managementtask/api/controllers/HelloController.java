package com.example.managementtask.api.controllers;

import com.example.managementtask.api.services.TaskServiceImpl;
import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final TaskServiceImpl taskService;
//    @GetMapping
//    public String sayHello(Authentication authentication){
//        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//        System.out.println(userPrincipal.getUsername()+" "+userPrincipal.getEmail()+" "+authentication);
//
//        return "Hello world";
//    }

    @GetMapping()
    public List<Task> readAllTask(){

        return taskService.readAllTask();
    }

    @PostMapping()
    public Task createTask(@RequestBody TaskDTO dto, Authentication authentication){
        return taskService.createTask(dto,authentication);
    }
}