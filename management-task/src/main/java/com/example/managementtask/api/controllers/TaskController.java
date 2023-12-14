package com.example.managementtask.api.controllers;

import com.example.managementtask.api.services.TaskServiceImpl;
import com.example.managementtask.store.dtos.PageDataDTO;
import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PageDataDTO<Task>> readAllTask(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readAllTask(PageRequest.of(page,size))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> readTaskById(@PathVariable Long id){
        return response.entity(taskService.readTaskById(id));
    }

    @GetMapping("/author")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByAuthor(
            Authentication authentication,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByAuthor(authentication,PageRequest.of(page,size))));
    }

    @GetMapping("/author/{name}")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByAuthorName(
            @PathVariable String name,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByAuthorName(name,PageRequest.of(page,size))));
    }

    @GetMapping("/executor")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByExecutor(
            Authentication authentication,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByExecutor(authentication,PageRequest.of(page,size))));
    }

    @GetMapping("/executor/{name}")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByExecutorName(
            @PathVariable String name,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByExecutorName(name,PageRequest.of(page,size))));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByPriority(
            @PathVariable Priority priority,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByPriority(priority,PageRequest.of(page,size))));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByStatus(
            @PathVariable Status status,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByStatus(status,PageRequest.of(page,size))));
    }

    @GetMapping("/priority_status/{priority}/{status}")
    public ResponseEntity<PageDataDTO<Task>> readAllTaskByPriorityAndStatus(
            @PathVariable Priority priority,
            @PathVariable Status status,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10")int size
    ){
        return response.listEntity(pageDataDTO(taskService.readTaskByTaskAndStatus(priority,status,PageRequest.of(page,size))));
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

    private PageDataDTO<Task> pageDataDTO(Page<Task> taskPage){
        return new PageDataDTO<>(taskPage.getContent(),taskPage.getTotalElements());
    }
}