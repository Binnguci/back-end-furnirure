package com.binnguci.furniture.service.account;

import com.binnguci.furniture.domain.request.AccountVerifyRequest;
import com.binnguci.furniture.domain.request.RegisterRequest;
import com.binnguci.furniture.dto.UserDTO;

public interface IAccountService {
    UserDTO register(RegisterRequest registerRequest);

    void forgetPassword(String email);

    void changePassword(String email, String password);

    void verifyAccount(AccountVerifyRequest accountVerifyRequest);
}
