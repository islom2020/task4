package com.example.task4.repository;

import com.example.task4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmailAndPassword(String email, String password);

    Optional<User> getUserByEmailAndPasswordAndStatusIsTrue(String email, String password);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    void deleteById(Long id);
}
