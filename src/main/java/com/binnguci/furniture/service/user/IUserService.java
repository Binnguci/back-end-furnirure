package com.binnguci.furniture.service.user;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.domain.request.AccountVerifyRequest;
import com.binnguci.furniture.domain.request.RegisterRequest;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();

    UserDTO getInformationOfUser(String username);

    UserDTO updateInformationOfUser(UserDTO userDTO);

    UserDTO findByUsername(String username);

    void blockAndUnBlockUser(Integer id);


}
