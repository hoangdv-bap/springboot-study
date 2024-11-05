package com.example.hdvdemojpa.service;

import com.example.hdvdemojpa.entity.User;
import com.example.hdvdemojpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(User user){
        userRepository.delete(user);
    }
}
