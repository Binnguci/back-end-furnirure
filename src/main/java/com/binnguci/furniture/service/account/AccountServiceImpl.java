package com.binnguci.furniture.service.account;

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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements IAccountService {
    IUserRepository userRepository;
    IRoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    IEmailService emailService;

    @Override
    public UserDTO register(RegisterRequest registerRequest) {
        log.info("Request to register user with username: {}", registerRequest.getUsername());
        validateRegisterRequest(registerRequest);
        String encodedPassword = encodePassword(registerRequest.getPassword());
        UserEntity user = createUserEntity(registerRequest, encodedPassword);
        assignDefaultRoles(user);
        user.setIsActive((short) 0);
        UserEntity savedUser = saveUser(user);
        UserDTO userDTO = AccountServiceImpl.this.findByUsername(savedUser.getUsername());
        log.info("Successfully registered user with username: {}", savedUser.getUsername());
        return userMapper.toDTO(savedUser);
    }

    /*kiểm tra xem người dùng đã có tài khoản hay chưa
     * 1. Nếu tài khoản đã có thì thông báo lỗi là tài khoản đã tồn tại
     * 2. Nếu email đã có thì kiểm tra xem tài khoản đó có xác thực hay chưa
     * 3. Nếu đã xác thực thì báo lỗi email đã tồn tại
     * 4. Nếu chưa xác thực thì tiến hành gửi lại một OTP mới và gửi lại mail cho ngừoi dùng để tiến hành xác thực
     **/
    private void validateRegisterRequest(RegisterRequest registerRequest) {
        if (checkUsernameExists(registerRequest.getUsername())) {
            return;
        }
        if (checkEmailExists(registerRequest.getEmail())) {
            UserEntity user = userRepository.findByEmail(registerRequest.getEmail()).get();
            if (user.getIsActive() == 1) {
                throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
            } else {
                Integer otp = generateOTP();
                user.setOtp(otp);
                emailService.sendMailOTP(user.getEmail(), otp);
            }
        }
    }

    private boolean checkUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void forgetPassword(String email) {
        log.info("Request to forget password for email: {}", email);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        int otp = generateOTP();
        user.setOtp(otp);
        user.setOtpExpired(Instant.now().atZone(ZoneId.systemDefault()).plusMinutes(10).toInstant());
        userRepository.save(user);
        emailService.sendMailOTP(user.getEmail(), otp);
        log.info("OTP sent successfully to email: {}", email);
    }

    public UserDTO findByUsername(String username) {
        log.info("Request to find user by username: {}", username);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        log.info("Successfully found user by id: {}", username);
        return userMapper.toDTO(user);
    }


    @Override
    public void changePassword(String email, String password) {
        log.info("Request to change password for email: {}", email);

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user.setPassword(encodePassword(password));
        userRepository.save(user);
        log.info("Password changed successfully for email: {}", email);
    }

    /*Khi người dùng gửi otp xác thực
     * 1. nếu đúng thì tiến hành lưu ngừoi dùng với kích hoạt tài khoản
     * 2. Sai thì tiếp tục báo lỗi*/
    @Override
    public void verifyAccount(AccountVerifyRequest accountVerifyRequest) {
        log.info("Request to verify account for email: {}", accountVerifyRequest.getEmail());

        UserEntity userEntity = userRepository.findByEmail(accountVerifyRequest.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (userEntity.getOtpExpired().isBefore(Instant.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        if (!userEntity.getOtp().equals(accountVerifyRequest.getOtp())) {
            throw new AppException(ErrorCode.INVALID_OTP);
        }
        userEntity.setIsActive((short) 1);
        userEntity.setOtp(null);
        userEntity.setOtpExpired(null);

        userRepository.save(userEntity);
        log.info("Account verified successfully for email: {}", accountVerifyRequest.getEmail());
    }

    private UserEntity createUserEntity(RegisterRequest registerRequest, String encodedPassword) {
        log.info("Request to create user entity");
        registerRequest.setPassword(encodedPassword);
        return userMapper.toRequestToEntity(registerRequest);
    }

    private void assignDefaultRoles(UserEntity user) {
        log.info("Assigning default role to user");
        RoleEntity role = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRole(role);
    }


    private UserEntity saveUser(UserEntity user) {
        log.info("Request to save user");
        return userRepository.save(user);
    }

    private String encodePassword(String password) {
        log.info("Request to encode password");
        return passwordEncoder.encode(password);
    }

    private int generateOTP() {
        return (int) (Math.random() * 900000) + 100000;
    }
}
