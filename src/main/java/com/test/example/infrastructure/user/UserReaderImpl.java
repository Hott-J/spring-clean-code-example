package com.test.example.infrastructure.user;

import com.test.example.common.exception.BusinessException;
import com.test.example.common.response.ErrorCode;
import com.test.example.domain.user.User;
import com.test.example.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;

    @Override
    public User getUser(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "user.byId", List.of(userId.toString())));
    }
    @Override
    public User getUser(String userToken) {
        return userRepository.findByUserToken(userToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "user.byToken", List.of(userToken)));
    }
}
