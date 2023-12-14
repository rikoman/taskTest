package com.example.managementtask.api.services;

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
    public Task createTask(TaskDTO task,Authentication authentication) {
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
        return taskRepository.findByAuthorUsername(name,pageRequest);
    }

    @Override
    public Page<Task> readTaskByExecutor(Authentication authentication, PageRequest pageRequest) {
        return taskRepository.findByExecutorId(userPrincipal(authentication).getId(),pageRequest);
    }

    @Override
    public Page<Task> readTaskByExecutorName(String name, PageRequest pageRequest) {
        return taskRepository.findByExecutorUsername(name,pageRequest);
    }

    @Override
    public Page<Task> readTaskByPriority(Priority priority, PageRequest pageRequest) {
        return taskRepository.findByPriority(priority,pageRequest);
    }

    @Override
    public Page<Task> readTaskByStatus(Status status, PageRequest pageRequest) {
        return taskRepository.findByStatus(status,pageRequest);
    }

    @Override
    public Page<Task> readTaskByTaskAndStatus(Priority priority, Status status, PageRequest pageRequest) {
        return taskRepository.findByPriorityAndStatus(priority,status,pageRequest);
    }

    @Override
    public Task updatePartInfoTask(Long id, TaskDTO task,Authentication authentication) {
        Task existTask = readTaskById(id);

        if(existTask.getAuthor().getId().equals(userPrincipal(authentication).getId())){
            if(task.getTitle()!= null) existTask.setTitle(task.getTitle());
            if(task.getDescription()!=null) existTask.setDescription(task.getDescription());
            if(task.getExecutor()!=null) existTask.setExecutor(userService.readUserById(task.getExecutor()));
            if(task.getStatus()!=null) existTask.setStatus(task.getStatus());
            if(task.getPriority()!=null) existTask.setPriority(task.getPriority());
        }
        if(existTask.getExecutor().getId().equals(userPrincipal(authentication).getId())){
            if(task.getStatus()!=null) existTask.setStatus(task.getStatus());
        }
//        if(!existTask.getExecutor().getId().equals(userPrincipal.getId())|| !existTask.getAuthor().getId().equals(userPrincipal.getId())){
//            throw new RuntimeException("Вы не являетесь автором или исполнителем");
//        }
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