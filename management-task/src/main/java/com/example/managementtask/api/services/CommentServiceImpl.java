package com.example.managementtask.api.services;

import com.example.managementtask.api.exception.NotFoundException;
import com.example.managementtask.api.services.interfaceService.CommentService;
import com.example.managementtask.security.service.UserDetailsImpl;
import com.example.managementtask.store.dtos.CommentDTO;
import com.example.managementtask.store.entities.Comment;
import com.example.managementtask.store.entities.Task;
import com.example.managementtask.store.repositories.CommentRepository;
import com.example.managementtask.store.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TaskServiceImpl taskService;
    private final TaskRepository taskRepository;

    @Override
    public Comment createComment(CommentDTO dto, Authentication authentication) {
        Task task = taskService.readTaskById(dto.getTaskId());

        Comment newComment = Comment.builder()
                .content(dto.getContent())
                .author(userService.readUserById(userPrincipal(authentication).getId()))
                .task(task)
                .dateCreate(LocalDateTime.now())
                .build();

        Comment savedComment = commentRepository.save(newComment);

        task.getComments().add(savedComment);

        taskRepository.save(task);

        return savedComment;
    }

    @Override
    public List<Comment> readAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment updatePartInfoComment(Long id, CommentDTO dto, Authentication authentication) {
        Comment existComment = findCommentById(id);
        if(existComment.getAuthor().getId().equals(userPrincipal(authentication).getId())){
            existComment.setContent(dto.getContent());
        }
        return commentRepository.save(existComment);
    }


    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(findCommentById(id).getId());
    }

    private Comment findCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("comment with /%s/ id doesn' found",id)));
    }

    private UserDetailsImpl userPrincipal(Authentication authentication){
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}