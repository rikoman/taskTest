package com.example.managementtask.api.services.interfaceService;

import com.example.managementtask.store.dtos.TaskDTO;
import com.example.managementtask.store.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;

public interface TaskService {

    Task createTask(TaskDTO task,Authentication authentication);

    Page<Task> readAllTask(PageRequest pageRequest);

    Task readTaskById(Long id);

    Page<Task> readTaskByAuthor(Authentication authentication, PageRequest pageRequest);

    Page<Task> readTaskByAuthorName(String name, PageRequest pageRequest);

    Page<Task> readTaskByExecutor(Authentication authentication, PageRequest pageRequest);

    Page<Task> readTaskByExecutorName(String name, PageRequest pageRequest);

    Page<Task> readTaskByPriority(String priority, PageRequest pageRequest);

    Page<Task> readTaskByStatus(String status, PageRequest pageRequest);

    Task updatePartInfoTask(Long id,TaskDTO task,Authentication authentication);

    void deleteTask(Long id);
}