package com.group11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group11.config.JwtProvider;
import com.group11.models.User;
import com.group11.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User reqUser) {
        User user = new User();
        user.setFirstName(reqUser.getFirstName());
        user.setLastName(reqUser.getLastName());
        user.setEmail(reqUser.getEmail());
        user.setPassword(reqUser.getPassword());
        user.setRole(reqUser.getRole());
        User creatUser = userRepository.save(user);
        return creatUser;
    }

    @Override
    public User findUserFromJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not exist with userid " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
    
}
