package com.binnguci.furniture.service.authentication;


import com.binnguci.furniture.domain.request.AuthenticationRequest;
import com.binnguci.furniture.domain.request.LogoutRequest;
import com.binnguci.furniture.domain.response.JwtResponse;

public interface IAuthenticationService {
    JwtResponse login(AuthenticationRequest request);

    void logout(LogoutRequest request);
}