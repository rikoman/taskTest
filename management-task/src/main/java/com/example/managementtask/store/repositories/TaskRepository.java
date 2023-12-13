package com.example.managementtask.store.repositories;

import com.example.managementtask.store.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByAuthorId(Long id);

    List<Task> findByAuthorUsername(String username);

    List<Task> findByExecutorId(Long id);
}