package com.example.managementtask.api.services;

import com.example.managementtask.api.exception.NotFoundException;
import com.example.managementtask.store.repositories.UserRepository;
import com.example.managementtask.store.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User readUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public void readUserByUsername(String username){
        userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(String.format("user with /%s/ name not found", username)));
    }

    public List<User> readAllUserByIds(List<Long> userIds){
        return userRepository.findAllById(userIds);
    }
}