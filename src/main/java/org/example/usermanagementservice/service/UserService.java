package org.example.usermanagementservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.usermanagementservice.exception.AlreadyExistsException;
import org.example.usermanagementservice.model.Role;
import org.example.usermanagementservice.model.User;
import org.example.usermanagementservice.payload.UserPayload;
import org.example.usermanagementservice.repository.UserRepository;
import org.springframework.stereotype.Service;



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
}
