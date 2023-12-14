package com.example.managementtask.store.repositories;

import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Page<Task> findByAuthorId(Long id,PageRequest pageRequest);

    Page<Task> findByAuthorUsername(String username,PageRequest pageRequest);

    Page<Task> findByExecutorId(Long id,PageRequest pageRequest);

    Page<Task> findByExecutorUsername(String username,PageRequest pageRequest);

    Page<Task> findByPriority(Priority priority,PageRequest pageRequest);

    Page<Task> findByStatus(Status status,PageRequest pageRequest);
}