package com.example.managementtask.api.services;

import com.example.managementtask.api.services.interfaseService.CommentService;
import com.example.managementtask.security.service.UserDetailsImpl;
import com.example.managementtask.store.dtos.CommentDTO;
import com.example.managementtask.store.entities.Comment;
import com.example.managementtask.store.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public Comment createComment(CommentDTO comment, Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Comment newComment = Comment.builder()
                .content(comment.getContent())
                .author(userService.readUserById(userPrincipal.getId()))
                .build();
        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> readAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment readCommentById(Long id) {
        return null;
    }

    @Override
    public Comment updatePartInfoComment(Long id, CommentDTO comment, Authentication authentication) {
        return null;
    }


    @Override
    public void deleteComment(Long id) {

    }
}