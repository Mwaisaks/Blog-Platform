package com.devtiro.Blog.Platform.service.impl;

import com.devtiro.Blog.Platform.domain.entity.User;
import com.devtiro.Blog.Platform.repository.UserRepository;
import com.devtiro.Blog.Platform.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
}