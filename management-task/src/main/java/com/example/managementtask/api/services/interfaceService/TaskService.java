package com.example.managementtask.api.services.interfaseService;

import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TaskService {

    Task createTask(TaskDTO task,Authentication authentication);

    List<Task> readAllTask();

    Task readTaskById(Long id);

    List<Task> readTaskByAuthor(Authentication authentication);

    List<Task> readTaskByAuthorName(String name);

    List<Task> readTaskByExecutor(Authentication authentication);

    Task updatePartInfoTask(Long id,TaskDTO task,Authentication authentication);

    void deleteTask(Long id);
}