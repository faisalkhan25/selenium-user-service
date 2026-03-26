package com.code.userservice.service;

import com.code.userservice.entities.User;
import com.code.userservice.exceptions.ResourceNotFoundException;
import com.code.userservice.models.UserRequest;
import com.code.userservice.models.UserResponse;
import com.code.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .build();
        return userRepository.save(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("This Id is not Found"));
        return  new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .toList();
    }

    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("This User Does Not Exist")
        );
        userRepository.deleteById(id);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("This User Does Not Exist")
        );
        existingUser.setName(userRequest.name());
        existingUser.setEmail(userRequest.email());
        existingUser = userRepository.save(existingUser);
        return new UserResponse(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
    }
}
