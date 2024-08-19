package com.binnguci.furniture.service.user;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.dto.request.RegisterRequest;
import com.binnguci.furniture.dto.response.APIResponse;

public interface IUserService {
    UserDTO register(RegisterRequest registerRequest);
}
