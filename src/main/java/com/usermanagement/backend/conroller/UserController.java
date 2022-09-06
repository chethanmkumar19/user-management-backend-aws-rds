package com.usermanagement.backend.conroller;


import com.usermanagement.backend.entity.User;
import com.usermanagement.backend.exception.GeneralRuntimeException;
import com.usermanagement.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("user/")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        try {
            return userService.findById(id);
        } catch (GeneralRuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public User removeUserById(@PathVariable Long id) {
        try {
            return userService.removeById(id);
        } catch (GeneralRuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @PostMapping("/deleteSelected")
    public ResponseEntity<String> removeUserById(@RequestBody Collection<Long> ids) {
        return new ResponseEntity<>(userService.removeAllByIds(ids), HttpStatus.OK);
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        try {
            return userService.add(user);
        } catch (GeneralRuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

}
