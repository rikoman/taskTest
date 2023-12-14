package com.example.managementtask.api.services;

import com.example.managementtask.api.exception.BadRequestException;
import com.example.managementtask.api.exception.NotFoundException;
import com.example.managementtask.api.services.interfaceService.TaskService;
import com.example.managementtask.security.service.UserDetailsImpl;
import com.example.managementtask.store.entities.user.User;
import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import com.example.managementtask.store.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public Task createTask(TaskDTO task, Authentication authentication) {
        User executor = task.getExecutor() !=null ? userService.readUserById(task.getExecutor()):userService.readUserById(userPrincipal(authentication).getId());
        Task newTask = Task.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(userService.readUserById(userPrincipal(authentication).getId()))
                .executor(executor)
                .build();
        return taskRepository.save(newTask);
    }

    @Override
    public Page<Task> readAllTask(PageRequest pageRequest) {
        return taskRepository.findAll(pageRequest);
    }

    @Override
    public Task readTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("task with /%s/ id doesn' found",id)));
    }

    @Override
    public Page<Task> readTaskByAuthor(Authentication authentication, PageRequest pageRequest) {
        return taskRepository.findByAuthorId(userPrincipal(authentication).getId(),pageRequest);
    }

    @Override
    public Page<Task> readTaskByAuthorName(String name, PageRequest pageRequest) {
        userService.readUserByUsername(name);
        return taskRepository.findByAuthorUsername(name,pageRequest);
    }

    @Override
    public Page<Task> readTaskByExecutor(Authentication authentication, PageRequest pageRequest) {
        return taskRepository.findByExecutorId(userPrincipal(authentication).getId(),pageRequest);
    }

    @Override
    public Page<Task> readTaskByExecutorName(String name, PageRequest pageRequest) {
        userService.readUserByUsername(name);
        return taskRepository.findByExecutorUsername(name,pageRequest);
    }

    @Override
    public Page<Task> readTaskByPriority(String priority, PageRequest pageRequest) {
        try{
            Priority priorityEnum = Priority.valueOf(priority.toUpperCase());
            return taskRepository.findByPriority(priorityEnum,pageRequest);
        }catch (IllegalArgumentException ex) {
            throw new BadRequestException("Неверное значение параметра priority");
        }
    }

    @Override
    public Page<Task> readTaskByStatus(String status, PageRequest pageRequest) {
       try{
           Status statusEnum = Status.valueOf(status.toUpperCase());
           return taskRepository.findByStatus(statusEnum,pageRequest);
       }catch (IllegalArgumentException ex) {
           throw new BadRequestException("Неверное значение параметра priority");
       }
    }

    @Override
    public Task updatePartInfoTask(Long id, TaskDTO dto,Authentication authentication) {
        Task existTask = readTaskById(id);

        if(!existTask.getAuthor().getId().equals(userPrincipal(authentication).getId()) && !existTask.getExecutor().getId().equals(userPrincipal(authentication).getId())){
            throw new BadRequestException("Вы не являетесь автором или исполнителем");
        }

        if(existTask.getAuthor().getId().equals(userPrincipal(authentication).getId())){
            if(dto.getTitle()!= null) existTask.setTitle(dto.getTitle());
            if(dto.getDescription()!=null) existTask.setDescription(dto.getDescription());
            if(dto.getExecutor()!=null) existTask.setExecutor(userService.readUserById(dto.getExecutor()));
            if(dto.getStatus()!=null) existTask.setStatus(dto.getStatus());
            if(dto.getPriority()!=null) existTask.setPriority(dto.getPriority());
        }

        if(existTask.getExecutor().getId().equals(userPrincipal(authentication).getId())){
            if(dto.getTitle()!= null || dto.getDescription()!=null || dto.getExecutor()!=null || dto.getPriority()!=null)
                throw new BadRequestException("Вы не можете обновить поля, кроме поля status");
        }

        return taskRepository.save(existTask);
    }

    @Override
    public void deleteTask(Long id) {
        readTaskById(id);
        taskRepository.deleteById(id);
    }

    private UserDetailsImpl userPrincipal(Authentication authentication){
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}