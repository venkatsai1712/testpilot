package com.venkatsai.testpilot.authentication.repository;

import com.venkatsai.testpilot.authentication.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthenticationRepository extends JpaRepository<User, UUID> {
    User findByEmail(@NotBlank(message = "Name cannot be blank") @Email(message = "Invalid Email") String email);
}
