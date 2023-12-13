package com.example.managementtask.store.repositories;

import com.example.managementtask.store.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}