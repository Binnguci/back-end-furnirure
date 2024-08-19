package com.binnguci.furniture.service.authentication;


import com.binnguci.furniture.dto.request.AuthenticationRequest;
import com.binnguci.furniture.dto.request.LogoutRequest;
import com.binnguci.furniture.dto.response.JwtResponse;

public interface IAuthenticationService {
    JwtResponse login(AuthenticationRequest request);

    void logout(LogoutRequest request);
}