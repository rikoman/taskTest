package com.example.managementtask.store.repositories;

import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByAuthorId(Long id);

    List<Task> findByAuthorUsername(String username);

    List<Task> findByExecutorId(Long id);

    List<Task> findByExecutorUsername(String username);

    List<Task> findByPriority(Priority priority);

    List<Task> findByStatus(Status status);

    List<Task> findByPriorityAndStatus(Priority priority, Status status);
}