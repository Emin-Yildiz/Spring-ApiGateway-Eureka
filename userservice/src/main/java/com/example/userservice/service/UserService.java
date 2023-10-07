package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    public String updateUserById(User user, UUID id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            User user_f = foundUser.get();
            user_f.setName(user.getName());
            userRepository.save(user_f);
            return "Update success";
        }else
            return "Update failed";
    }

    public String deleteUserById(UUID id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            userRepository.delete(foundUser.get());
            return "Delete success";
        }else
            return "Delete failed";
    }
}
