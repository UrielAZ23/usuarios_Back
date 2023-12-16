package com.ControlUsuarios.service;

import com.ControlUsuarios.persitence.entity.UserEntity;
import com.ControlUsuarios.persitence.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServer {

    private final UserRepository userRepository;

    @Autowired
    public UserServer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        List<UserEntity> users = this.userRepository.findAll();
        return users;
    }

    public UserEntity getUserById(String id) {
        return this.userRepository.findById(id).orElse(null);

    }

    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    public void delete(String username){
        this.userRepository.deleteById(username);
    }

    public Boolean exist(String username) {
        return this.userRepository.existsById(username);
    }


}
