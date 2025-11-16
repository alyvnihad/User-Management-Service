package org.example.usermanagementservice.repository;


import org.example.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(Long phoneNumber);
}
