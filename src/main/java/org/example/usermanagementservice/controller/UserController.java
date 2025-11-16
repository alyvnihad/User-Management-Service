package org.example.usermanagementservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.usermanagementservice.payload.UserPayload;
import org.example.usermanagementservice.service.UserService;
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
}
