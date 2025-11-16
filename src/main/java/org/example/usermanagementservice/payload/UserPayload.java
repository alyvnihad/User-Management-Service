package org.example.usermanagementservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPayload {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email format is incorrect")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    private Long phoneNumber;
    private String role;
}
