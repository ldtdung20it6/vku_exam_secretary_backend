package com.group11.service;

import com.group11.models.User;

public interface UserService {

    public User findUserFromJwt(String jwt);

    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);
}
