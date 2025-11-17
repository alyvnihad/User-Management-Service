package org.example.usermanagementservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.usermanagementservice.dto.UserDTO;
import org.example.usermanagementservice.exception.AlreadyExistsException;
import org.example.usermanagementservice.exception.NotFoundException;
import org.example.usermanagementservice.model.Role;
import org.example.usermanagementservice.model.User;
import org.example.usermanagementservice.payload.UserPayload;
import org.example.usermanagementservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Register a new user.
    @Transactional
    public void register(UserPayload payload) {
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new AlreadyExistsException("Email already in use");
        }
        if (userRepository.existsByPhoneNumber(payload.getPhoneNumber())) {
            throw new AlreadyExistsException("Phone number already in use");
        }

        User user = new User();
        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        user.setPhoneNumber(payload.getPhoneNumber());
        user.setRole(Role.valueOf(payload.getRole()));
        userRepository.save(user);
    }

    // Get a user by ID.
    public UserDTO getUserById(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found id")));
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.get().getName());
        userDTO.setEmail(user.get().getEmail());
        userDTO.setPhoneNumber(user.get().getPhoneNumber());
        userDTO.setRole(String.valueOf(user.get().getRole()));

        return userDTO;
    }

    // Get a paginated list of users.
    public Page<UserDTO> getAllUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        return users.map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setRole(String.valueOf(user.getRole()));
            return userDTO;
        });
    }
    // Update user details
    @Transactional
    public void userUpdate(Long id, UserPayload payload) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found id"));

        if (user.getEmail().equals(payload.getEmail()) || userRepository.existsByEmail(payload.getEmail())) {
            throw new AlreadyExistsException("Email already in use");
        }

        if (user.getPhoneNumber().equals(payload.getPhoneNumber()) || userRepository.existsByPhoneNumber(payload.getPhoneNumber())) {
            throw new AlreadyExistsException("Phone number already in use");
        }

        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        user.setPhoneNumber(payload.getPhoneNumber());
        userRepository.save(user);
    }

    // Delete a user by ID.
    @Transactional
    public void userDelete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found id"));

        userRepository.delete(user);
    }
}
