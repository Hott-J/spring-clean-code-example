package com.test.example.domain.user;

public interface UserReader {

    User getUser(String userToken);
    User getUser(Long userId);
}
