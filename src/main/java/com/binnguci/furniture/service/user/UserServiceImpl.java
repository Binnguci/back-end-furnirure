package com.binnguci.furniture.service.user;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.entity.UserEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.UserMapper;
import com.binnguci.furniture.repository.IRoleRepository;
import com.binnguci.furniture.repository.IUserRepository;
import com.binnguci.furniture.service.email.IEmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements IUserService {
    IUserRepository userRepository;
    IRoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    IEmailService emailService;

    @Override
    public List<UserDTO> findAll() {
        log.info("Request to get all users in service");
        List<UserEntity> userEntities = userRepository.findAll();
        if (!userEntities.isEmpty()) {
            log.info("Successfully found all users");
            return userMapper.toListDTO(userEntities);
        }
        return List.of();
    }


    @Override
    public UserDTO getInformationOfUser(String username) {
        log.info("Request to get information of user with username: {}", username);
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        log.info("Successfully found user with username: {}", username);
        return userMapper.toDTO(userEntity);
    }

    @Override
    public UserDTO updateInformationOfUser(UserDTO userDTO) {
        log.info("Request to update information of user with username: {}", userDTO.getUsername());

        UserEntity userEntity = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userEntity.setFullName(userDTO.getFullName());
        userEntity.setPhone(userDTO.getPhone());
        return null;
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("Request to find user by username: {}", username);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        log.info("Successfully found user by id: {}", username);
        return userMapper.toDTO(user);
    }

    @Override
    public void blockAndUnBlockUser(Integer id) {
        log.info("Request to block user with id: {}", id);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (user.getIsActive()== 1) {
            user.setIsActive( (short) 0);
        } else {
            user.setIsActive((short) 0);
        }
        userRepository.save(user);
        log.info("User with id: {} blocked/unblocked successfully", id);
    }
}

