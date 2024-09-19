package com.binnguci.furniture.service.user;

import com.binnguci.furniture.domain.request.AccountVerifyRequest;
import com.binnguci.furniture.domain.request.RegisterRequest;
import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.entity.RoleEntity;
import com.binnguci.furniture.entity.UserEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.UserMapper;
import com.binnguci.furniture.repository.IRoleRepository;
import com.binnguci.furniture.repository.IUserRepository;
import com.binnguci.furniture.service.email.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IEmailService emailService;

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
    public UserDTO register(RegisterRequest registerRequest) {
        log.info("Request to register user with username: {}", registerRequest.getUsername());
        validateRegisterRequest(registerRequest);
        String encodedPassword = encodePassword(registerRequest.getPassword());
        UserEntity user = createUserEntity(registerRequest, encodedPassword);
        assignDefaultRoles(user);

        // tạo và gán otp
        String otp = generateOTP();
        user.setOtp(otp);
        user.setOtpExpiry(
                Instant.now().atZone(ZoneId.systemDefault()).plusMinutes(10).toInstant()
        );

        //Gửi mail
        emailService.sendMailOTP(user.getEmail(), otp);

        UserEntity savedUser = saveUser(user);
        UserDTO userDTO = UserServiceImpl.this.findByUsername(savedUser.getUsername());
        log.info("Successfully registered user with username: {}", savedUser.getUsername());
        return userMapper.toDTO(savedUser);
    }

    @Override
    public void forgetPassword(String email) {
        log.info("Request to forget password for email: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // tạo và gán otp
        String otp = generateOTP();
        user.setOtp(otp);
        user.setOtpExpiry(Instant.now().atZone(ZoneId.systemDefault()).plusMinutes(10).toInstant());
        userRepository.save(user);
        //Gửi mail
        emailService.sendMailOTP(user.getEmail(), otp);
        log.info("OTP sent successfully to email: {}", email);
    }

    @Override
    public void changePassword(String email, String password) {
        log.info("Request to change password for email: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user.setPassword(encodePassword(password));
        userRepository.save(user);
        log.info("Password changed successfully for email: {}", email);
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
        userEntity.setAddress(userDTO.getAddress());
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
        if (user.getEnabled() == 1) {
            user.setEnabled((short) 0);
        } else {
            user.setEnabled((short) 1);
        }
        userRepository.save(user);
        log.info("User with id: {} blocked/unblocked successfully", id);
    }

    @Override
    public void verifyAccount(AccountVerifyRequest accountVerifyRequest) {
        log.info("Request to verify account for email: {}", accountVerifyRequest.getEmail());

        UserEntity userEntity = userRepository.findByEmail(accountVerifyRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Check otp expiry
        if (userEntity.getOtpExpiry().isBefore(Instant.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        // Check otp is valid
        if (!userEntity.getOtp().equals(accountVerifyRequest.getOtp())) {
            throw new AppException(ErrorCode.INVALID_OTP);
        }

        // Gán dữ liệu mới
        userEntity.setEnabled((short) 1);
        userEntity.setOtp(null);
        userEntity.setOtpExpiry(null);

        userRepository.save(userEntity);
        log.info("Account verified successfully for email: {}", accountVerifyRequest.getEmail());
    }


    private void validateRegisterRequest(RegisterRequest registerRequest) {
        log.info("Request to validate register request");
        Optional<UserEntity> userByName = userRepository.findByUsername(registerRequest.getUsername());
        Optional<UserEntity> userByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (userByName.isPresent()) {
            log.warn("Username already exists");
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if (userByEmail.isPresent()) {
            log.warn("Email already exists");
            UserEntity existingUser = userByEmail.get();
            if (existingUser.getEnabled() == 0) {

                String newOtp = generateOTP();
                existingUser.setOtp(newOtp);
                existingUser.setOtpExpiry(Instant.now().atZone(ZoneId.systemDefault()).plusMinutes(10).toInstant());
                userRepository.save(existingUser);
                // gửi lại otp
                emailService.sendMailOTP(existingUser.getEmail(), newOtp);

                throw new AppException(ErrorCode.ACCOUNT_NOT_VERIFIED);
            } else {
                throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
            }
        }
    }

    private void assignDefaultRoles(UserEntity user) {
        log.info("Assigning default role to user");
        RoleEntity role = roleRepository.findById(1)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRole(role);
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

    private String generateOTP() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

}

