package com.binnguci.furniture.config;

import com.binnguci.furniture.constant.RoleConstant;
import com.binnguci.furniture.constant.StringConstant;
import com.binnguci.furniture.entity.UserEntity;
import com.binnguci.furniture.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Request to load user by username");

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(StringConstant.USER_NOT_FOUND + " : " + username));

        SimpleGrantedAuthority authority = user.getRole() != null
                ? new SimpleGrantedAuthority(user.getRole().getName())
                : new SimpleGrantedAuthority(RoleConstant.USER);

        log.info("User found: {}", user.getUsername());

        return new User(user.getUsername(), user.getPassword(), List.of(authority));
    }
}
