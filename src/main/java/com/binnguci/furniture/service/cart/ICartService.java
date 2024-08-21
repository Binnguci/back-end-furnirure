package com.binnguci.furniture.service.cart;

import com.binnguci.furniture.dto.CartDTO;

public interface ICartService {
    CartDTO findByUserId(Integer userId);
}
