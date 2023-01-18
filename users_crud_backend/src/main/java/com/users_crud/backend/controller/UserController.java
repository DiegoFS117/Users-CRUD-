package com.users_crud.backend.controller;

import com.users_crud.backend.model.User;

import com.users_crud.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/usuario")
    User addUser(@RequestBody User addUser) {
        return userService.save(addUser);
    }

    @GetMapping("/usuarios")
    Page<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/usuario/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/usuario/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.update(newUser, id);
    }

    @DeleteMapping("/usuario/{id}")
    String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
