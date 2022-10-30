package com.example.petstoreclone.service;

import com.example.petstoreclone.entity.User;
import com.example.petstoreclone.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        user.setToken(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }
    public List<User> saveAll(List<User> users){
        for(User u : users){
            save(u);
        }
        return users;
    }

    public Optional<User> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
