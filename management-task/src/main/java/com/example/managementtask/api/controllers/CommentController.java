package com.example.managementtask.api.controllers;

import com.example.managementtask.api.services.CommentServiceImpl;
import com.example.managementtask.store.dtos.CommentDTO;
import com.example.managementtask.store.entities.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentServiceImpl commentService;

    @GetMapping
    public List<Comment> readAllComment(){
        return commentService.readAllComment();
    }

    @PostMapping
    public Comment createComment(CommentDTO dto, Authentication authentication){
        return commentService.createComment(dto,authentication);
    }

}