package com.example.managementtask.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDataDTO<T> {
    private List<T> data;
    private Long total;
}