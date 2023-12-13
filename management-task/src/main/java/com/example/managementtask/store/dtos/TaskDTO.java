package com.example.managementtask.store.dtos;

import com.example.managementtask.store.entities.Comment;
import com.example.managementtask.store.entities.enums.Priority;
import com.example.managementtask.store.entities.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskDTO {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Long author;
    private Long executor;
    private List<Comment> comments;
}