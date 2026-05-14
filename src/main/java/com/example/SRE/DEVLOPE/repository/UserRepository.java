package com.example.SRE.DEVLOPE.repository;

import com.example.SRE.DEVLOPE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}