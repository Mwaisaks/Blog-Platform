package com.devtiro.Blog.Platform.service;

import com.devtiro.Blog.Platform.domain.entity.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);
}
