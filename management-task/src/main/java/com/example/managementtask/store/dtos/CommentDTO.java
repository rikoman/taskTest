package com.example.managementtask.store.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {
    private String content;
    private Long author;
    private Long taskId;
}