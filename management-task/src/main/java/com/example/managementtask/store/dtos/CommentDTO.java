package com.example.managementtask.store.dtos;

import com.example.managementtask.security.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {
    private String content;
    private Long author;

}