package com.test.example.infrastructure.user;

import com.test.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserToken(String userToken);
    Optional<User> findByUserId(Long userId);
}
