package org.example.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "auth")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
}
