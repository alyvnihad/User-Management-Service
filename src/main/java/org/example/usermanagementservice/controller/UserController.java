package org.example.usermanagementservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.usermanagementservice.dto.UserDTO;
import org.example.usermanagementservice.payload.UserPayload;
import org.example.usermanagementservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserPayload payload) {
        userService.register(payload);
        log.info("User create success");
        return ResponseEntity.ok("User create successfully");
    }

    @GetMapping("/getId/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        log.info("Fetched user with id {}", id);
        return userService.getUserById(id);
    }

    @GetMapping("/all-users")
    public Page<UserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> userUpdate(@PathVariable Long id, @Valid @RequestBody UserPayload payload) {
        userService.userUpdate(id, payload);
        log.info("User update success");
        return ResponseEntity.ok("User Update successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> userDelete(@PathVariable Long id) {
        userService.userDelete(id);
        log.info("User deleting by id: {}", id);
        return ResponseEntity.ok("User Deleting successfully");
    }
}
