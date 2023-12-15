package com.example.managementtask.store.entities;

import com.example.managementtask.store.enums.Priority;
import com.example.managementtask.store.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "title not must blank")
    private String title;
    private String description;
    @NotNull(message = "status not must empty")
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull(message = "priority not must empty")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull(message = "author not must empty")
    private User author;
    @ManyToOne
    @JoinColumn(name = "executor_id")
    @NotNull(message = "executor not must empty")
    private User executor;
    @OneToMany(mappedBy = "task",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}