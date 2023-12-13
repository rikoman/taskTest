package com.example.managementtask.api.services;

import com.example.managementtask.api.services.interfaceService.TaskService;
import com.example.managementtask.security.service.UserDetailsImpl;
import com.example.managementtask.security.user.User;
import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import com.example.managementtask.store.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public Task createTask(TaskDTO task,Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        User executor = task.getExecutor() !=null ? userService.readUserById(task.getExecutor()):userService.readUserById(userPrincipal.getId());

        Task newTask = Task.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(userService.readUserById(userPrincipal.getId()))
                .executor(executor)
                .build();
        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> readAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task readTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Task> readTaskByAuthor(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return taskRepository.findByAuthorId(userPrincipal.getId());
    }

    @Override
    public List<Task> readTaskByAuthorName(String name) {
        return taskRepository.findByAuthorUsername(name);
    }

    @Override
    public List<Task> readTaskByExecutor(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return taskRepository.findByExecutorId(userPrincipal.getId());
    }

    @Override
    public List<Task> readTaskByExecutorName(String name) {
        return taskRepository.findByExecutorUsername(name);
    }

    @Override
    public List<Task> readTaskByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> readTaskByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> readTaskByTaskAndStatus(Priority priority, Status status) {
        return taskRepository.findByPriorityAndStatus(priority,status);
    }

    @Override
    public Task updatePartInfoTask(Long id, TaskDTO task,Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        Task existTask = readTaskById(id);

        if(existTask.getAuthor().getId().equals(userPrincipal.getId())){
            if(task.getTitle()!= null) existTask.setTitle(task.getTitle());
            if(task.getDescription()!=null) existTask.setDescription(task.getDescription());
            if(task.getExecutor()!=null) existTask.setExecutor(userService.readUserById(task.getExecutor()));
            if(task.getStatus()!=null) existTask.setStatus(task.getStatus());
            if(task.getPriority()!=null) existTask.setPriority(task.getPriority());
        }
        if(existTask.getExecutor().getId().equals(userPrincipal.getId())){
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
}