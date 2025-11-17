package org.example.usermanagementservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private Long phoneNumber;
    private String role;
}
