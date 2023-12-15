package com.example.managementtask.api.services.interfaceService;

import com.example.managementtask.store.dtos.CommentDTO;
import com.example.managementtask.store.entities.Comment;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {

    Comment createComment(CommentDTO dto, Authentication authentication);

    List<Comment> readAllComment();

    Comment updatePartInfoComment(Long id,CommentDTO dto,Authentication authentication);

    void deleteComment(Long id);
}