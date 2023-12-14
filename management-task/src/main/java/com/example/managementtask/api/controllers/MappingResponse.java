package com.example.managementtask.api.controllers;

import com.example.managementtask.store.dtos.PageDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MappingResponse <T>{
    private T entity;

    public ResponseEntity<T> entity(T entity){
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    public ResponseEntity<PageDataDTO<T>> listEntity(PageDataDTO<T> entity){
        return new ResponseEntity<>(entity,HttpStatus.OK);
    }
}