package com.users_crud.backend.service;

import com.users_crud.backend.exception.UserNotFoundException;
import com.users_crud.backend.model.User;
import com.users_crud.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAllUsers() {
        Pageable paging = PageRequest.ofSize(10);
                return userRepository.findAll(paging);
    }

    public User save(User user) {
        user.setEmail(String.join(";", user.getEmails()));
        return userRepository.save(user);
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setNombre(newUser.getNombre());
            user.setGenero(newUser.getGenero());
            user.setEmail(String.join(";", newUser.getEmails()));
            user.setStatus(newUser.getStatus());
            user.setProfile_pic(newUser.getProfile_pic());

            return userRepository.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));
    }

    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "Usuario eliminado exitosamente";
    }

}
