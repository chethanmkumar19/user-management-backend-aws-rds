package com.usermanagement.backend.service;

import com.usermanagement.backend.entity.User;
import com.usermanagement.backend.exception.GeneralRuntimeException;
import com.usermanagement.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) throws GeneralRuntimeException {
        return userRepository.findById(id)
                .orElseThrow(() -> new GeneralRuntimeException("User with id " + id + " not found"));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User add(User user) throws GeneralRuntimeException {
        if (existsByEmail(user.getEmail())) {
            throw new GeneralRuntimeException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    public User removeById(Long id) throws GeneralRuntimeException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralRuntimeException("User with id " + id + " no found"));
        userRepository.delete(user);
        return user;
    }

}
