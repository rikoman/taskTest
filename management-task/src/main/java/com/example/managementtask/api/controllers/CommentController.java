package com.example.managementtask.api.controllers;

import com.example.managementtask.api.services.CommentServiceImpl;
import com.example.managementtask.store.dtos.CommentDTO;
import com.example.managementtask.store.entities.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public Comment createComment(@RequestBody CommentDTO dto, Authentication authentication){
        return commentService.createComment(dto,authentication);
    }

}