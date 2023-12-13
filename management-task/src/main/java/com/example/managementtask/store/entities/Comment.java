package com.example.managementtask.store.entities;

import com.example.managementtask.security.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="task_id", nullable = false)
    @JsonIgnore
    private Task task;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateCreate;
}