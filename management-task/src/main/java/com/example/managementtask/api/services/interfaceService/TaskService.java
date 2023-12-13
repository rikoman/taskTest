package com.example.managementtask.api.services.interfaceService;

import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TaskService {

    Task createTask(TaskDTO task,Authentication authentication);

    List<Task> readAllTask();

    Task readTaskById(Long id);

    List<Task> readTaskByAuthor(Authentication authentication);

    List<Task> readTaskByAuthorName(String name);

    List<Task> readTaskByExecutor(Authentication authentication);

    List<Task> readTaskByExecutorName(String name);

    List<Task> readTaskByPriority(Priority priority);

    List<Task> readTaskByStatus(Status status);

    List<Task> readTaskByTaskAndStatus(Priority priority, Status status);

    Task updatePartInfoTask(Long id,TaskDTO task,Authentication authentication);

    void deleteTask(Long id);
}