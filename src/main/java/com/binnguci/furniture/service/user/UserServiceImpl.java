package com.binnguci.furniture.service.user;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.dto.request.RegisterRequest;
import com.binnguci.furniture.entity.UserEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.UserMapper;
import com.binnguci.furniture.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(RegisterRequest registerRequest) {
        log.info("Request to register user with username: {}", registerRequest.getUsername());
        validateRegisterRequest(registerRequest);
        String encodedPassword = encodePassword(registerRequest.getPassword());
        UserEntity user = createUserEntity(registerRequest, encodedPassword);
        UserEntity savedUser = saveUser(user);
        log.info("Successfully registered user with username: {}", savedUser.getUsername());
        return userMapper.toDTO(savedUser);
    }

    private void validateRegisterRequest(RegisterRequest registerRequest) {
        log.info("Request to validate register request");
        Optional<UserEntity> userByName = userRepository.findByUsername(registerRequest.getUsername());
        Optional<UserEntity> userByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (userByName.isPresent()) {
            log.info("Username already exists");
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if (userByEmail.isPresent()) {
            log.info("Email already exists");
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }

    private String encodePassword(String password) {
        log.info("Request to encode password");
        return passwordEncoder.encode(password);
    }

    private UserEntity createUserEntity(RegisterRequest registerRequest, String encodedPassword) {
        log.info("Request to create user entity");
        registerRequest.setPassword(encodedPassword);
        return userMapper.toRequestToEntity(registerRequest);
    }

    private UserEntity saveUser(UserEntity user) {
        log.info("Request to save user");
        return userRepository.save(user);
    }

}
