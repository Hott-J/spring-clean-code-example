package com.test.example.domain.user;

import com.test.example.common.util.TokenGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private static final String USR_PREFIX = "usr_"; // 추가

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String userToken;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Builder
    public User(String name, String email, String password) {
        this.userToken = TokenGenerator.randomCharacterWithPrefix(USR_PREFIX);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
